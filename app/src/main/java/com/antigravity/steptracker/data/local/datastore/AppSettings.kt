package com.antigravity.steptracker.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "settings")

@Singleton
class AppSettings @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.dataStore

    companion object {
        val TRACKING_ENABLED = booleanPreferencesKey("tracking_enabled")
        val LAST_SENSOR_VALUE = floatPreferencesKey("last_sensor_value")
        val BASELINE_SENSOR = floatPreferencesKey("baseline_sensor")
        val BOOT_ID = longPreferencesKey("boot_id")
        val DAILY_GOAL = intPreferencesKey("daily_goal")
    }

    val trackingEnabled: Flow<Boolean> = dataStore.data.map { it[TRACKING_ENABLED] ?: false }
    val lastSensorValue: Flow<Float> = dataStore.data.map { it[LAST_SENSOR_VALUE] ?: 0f }
    val baselineSensor: Flow<Float> = dataStore.data.map { it[BASELINE_SENSOR] ?: 0f }
    val bootId: Flow<Long> = dataStore.data.map { it[BOOT_ID] ?: 0L }
    val dailyGoal: Flow<Int> = dataStore.data.map { it[DAILY_GOAL] ?: 10000 }

    suspend fun setTrackingEnabled(enabled: Boolean) {
        dataStore.edit { it[TRACKING_ENABLED] = enabled }
    }

    suspend fun setLastSensorValue(value: Float) {
        dataStore.edit { it[LAST_SENSOR_VALUE] = value }
    }

    suspend fun setBaselineSensor(value: Float) {
        dataStore.edit { it[BASELINE_SENSOR] = value }
    }

    suspend fun setBootId(id: Long) {
        dataStore.edit { it[BOOT_ID] = id }
    }

    suspend fun setDailyGoal(goal: Int) {
        dataStore.edit { it[DAILY_GOAL] = goal }
    }
}
