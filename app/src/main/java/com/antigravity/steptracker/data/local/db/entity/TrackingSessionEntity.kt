package com.antigravity.steptracker.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TrackingSession")
data class TrackingSessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val startTime: Long,
    val endTime: Long,
    val steps: Int,
    val distance: Float,
    val calories: Float,
    val duration: Long
)
