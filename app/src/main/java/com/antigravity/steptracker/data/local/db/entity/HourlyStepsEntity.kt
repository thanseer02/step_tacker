package com.antigravity.steptracker.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HourlySteps")
data class HourlyStepsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: Long,
    val hour: Int,
    val steps: Int
)
