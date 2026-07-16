package com.antigravity.steptracker.data.repository

import com.antigravity.steptracker.data.local.db.dao.StepDao
import com.antigravity.steptracker.data.local.db.entity.DailyStepsEntity
import com.antigravity.steptracker.domain.repository.StepRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StepRepositoryImpl @Inject constructor(
    private val stepDao: StepDao
) : StepRepository {
    override fun getDailyStepsFlow(date: Long): Flow<DailyStepsEntity?> {
        return stepDao.getDailyStepsByDateFlow(date)
    }

    override suspend fun getDailySteps(date: Long): DailyStepsEntity? {
        return stepDao.getDailyStepsByDate(date)
    }

    override suspend fun insertOrUpdateDailySteps(dailySteps: DailyStepsEntity) {
        val existing = stepDao.getDailyStepsByDate(dailySteps.date)
        if (existing != null) {
            stepDao.updateDailySteps(dailySteps.copy(id = existing.id))
        } else {
            stepDao.insertDailySteps(dailySteps)
        }
    }

    override fun getAllDailyStepsFlow(): Flow<List<DailyStepsEntity>> {
        return stepDao.getAllDailyStepsFlow()
    }
}
