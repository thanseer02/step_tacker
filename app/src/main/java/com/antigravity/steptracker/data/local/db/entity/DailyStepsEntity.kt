package com.antigravity.steptracker.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DailySteps")
data class DailyStepsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: Long,
    val steps: Int,
    val distance: Float,
    val calories: Float,
    val walkingTime: Long,
    val goal: Int,
    val createdAt: Long
)
