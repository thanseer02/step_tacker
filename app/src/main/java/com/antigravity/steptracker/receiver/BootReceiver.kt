package com.antigravity.steptracker.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.antigravity.steptracker.data.local.datastore.AppSettings
import com.antigravity.steptracker.service.StepTrackingService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {

    @Inject lateinit var appSettings: AppSettings

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED || 
            intent.action == Intent.ACTION_MY_PACKAGE_REPLACED ||
            intent.action == "android.intent.action.QUICKBOOT_POWERON") {
            
            val pendingResult = goAsync()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val isTracking = appSettings.trackingEnabled.first()
                    if (isTracking) {
                        StepTrackingService.start(context)
                    }
                } finally {
                    pendingResult.finish()
                }
            }
        }
    }
}
