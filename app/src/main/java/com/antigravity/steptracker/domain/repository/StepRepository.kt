package com.antigravity.steptracker.domain.repository

import com.antigravity.steptracker.data.local.db.entity.DailyStepsEntity
import kotlinx.coroutines.flow.Flow

interface StepRepository {
    fun getDailyStepsFlow(date: Long): Flow<DailyStepsEntity?>
    suspend fun getDailySteps(date: Long): DailyStepsEntity?
    suspend fun insertOrUpdateDailySteps(dailySteps: DailyStepsEntity)
    fun getAllDailyStepsFlow(): Flow<List<DailyStepsEntity>>
}
