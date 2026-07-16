package com.antigravity.steptracker.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.antigravity.steptracker.R
import com.antigravity.steptracker.data.local.datastore.AppSettings
import com.antigravity.steptracker.data.local.db.entity.DailyStepsEntity
import com.antigravity.steptracker.domain.repository.StepRepository
import com.antigravity.steptracker.utils.StepCalculator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class StepTrackingService : Service(), SensorEventListener {

    @Inject lateinit var stepRepository: StepRepository
    @Inject lateinit var appSettings: AppSettings

    private lateinit var sensorManager: SensorManager
    private var stepSensor: Sensor? = null
    
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var isTracking = false

    companion object {
        const val CHANNEL_ID = "step_tracker_channel"
        const val NOTIFICATION_ID = 1
        
        fun start(context: Context) {
            val intent = Intent(context, StepTrackingService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }
        
        fun stop(context: Context) {
            val intent = Intent(context, StepTrackingService::class.java)
            context.stopService(intent)
        }
    }

    override fun onCreate() {
        super.onCreate()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!isTracking) {
            startForegroundService()
            registerSensor()
            isTracking = true
        }
        return START_STICKY
    }

    private fun startForegroundService() {
        val notification = createNotification("Tracking active", 0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(NOTIFICATION_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_HEALTH)
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    private fun createNotification(content: String, steps: Int): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(content)
            .setContentText("Today's Steps: $steps")
            .setSmallIcon(android.R.drawable.sym_def_app_icon)
            .setOngoing(true)
            .build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Step Tracker Tracking",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun registerSensor() {
        stepSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
            val sensorValue = event.values[0]
            serviceScope.launch {
                handleSensorUpdate(sensorValue)
            }
        }
    }

    private suspend fun handleSensorUpdate(sensorValue: Float) {
        val trackingEnabled = appSettings.trackingEnabled.first()
        if (!trackingEnabled) {
            stopSelf()
            return
        }

        var baseline = appSettings.baselineSensor.first()
        if (baseline == 0f || sensorValue < baseline) {
            baseline = sensorValue
            appSettings.setBaselineSensor(baseline)
        }

        val todaySteps = (sensorValue - baseline).toInt()
        val currentTime = System.currentTimeMillis()
        val midnight = StepCalculator.getMidnightEpoch(currentTime)

        var dailySteps = stepRepository.getDailySteps(midnight)
        if (dailySteps == null) {
            baseline = sensorValue
            appSettings.setBaselineSensor(baseline)
            val newSteps = 0
            dailySteps = DailyStepsEntity(
                date = midnight,
                steps = newSteps,
                distance = 0f,
                calories = 0f,
                walkingTime = 0,
                goal = appSettings.dailyGoal.first(),
                createdAt = currentTime
            )
            stepRepository.insertOrUpdateDailySteps(dailySteps)
            updateNotification(newSteps)
        } else {
            val updated = dailySteps.copy(steps = todaySteps)
            stepRepository.insertOrUpdateDailySteps(updated)
            updateNotification(todaySteps)
        }
    }

    private fun updateNotification(steps: Int) {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, createNotification("Tracking active", steps))
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onDestroy() {
        super.onDestroy()
        isTracking = false
        sensorManager.unregisterListener(this)
        serviceScope.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
