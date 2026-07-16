package com.antigravity.steptracker.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antigravity.steptracker.data.local.db.entity.DailyStepsEntity
import com.antigravity.steptracker.data.local.db.entity.HourlyStepsEntity
import com.antigravity.steptracker.data.local.db.entity.SensorLogsEntity
import com.antigravity.steptracker.data.local.db.entity.TrackingSessionEntity

@Database(
    entities = [
        DailyStepsEntity::class,
        HourlyStepsEntity::class,
        SensorLogsEntity::class,
        TrackingSessionEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    // abstract fun stepDao(): StepDao
}
