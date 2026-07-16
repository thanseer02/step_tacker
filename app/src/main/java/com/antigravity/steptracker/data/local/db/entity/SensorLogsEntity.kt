package com.antigravity.steptracker.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SensorLogs")
data class SensorLogsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val timestamp: Long,
    val sensorValue: Float,
    val bootId: Long
)
