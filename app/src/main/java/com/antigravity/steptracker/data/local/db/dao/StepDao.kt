package com.antigravity.steptracker.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.antigravity.steptracker.data.local.db.entity.DailyStepsEntity
import com.antigravity.steptracker.data.local.db.entity.HourlyStepsEntity
import com.antigravity.steptracker.data.local.db.entity.SensorLogsEntity
import com.antigravity.steptracker.data.local.db.entity.TrackingSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StepDao {
    @Query("SELECT * FROM DailySteps WHERE date = :date LIMIT 1")
    suspend fun getDailyStepsByDate(date: Long): DailyStepsEntity?

    @Query("SELECT * FROM DailySteps WHERE date = :date LIMIT 1")
    fun getDailyStepsByDateFlow(date: Long): Flow<DailyStepsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailySteps(dailySteps: DailyStepsEntity)

    @Update
    suspend fun updateDailySteps(dailySteps: DailyStepsEntity)

    @Query("SELECT * FROM HourlySteps WHERE date = :date AND hour = :hour LIMIT 1")
    suspend fun getHourlySteps(date: Long, hour: Int): HourlyStepsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHourlySteps(hourlySteps: HourlyStepsEntity)

    @Update
    suspend fun updateHourlySteps(hourlySteps: HourlyStepsEntity)

    @Insert
    suspend fun insertSensorLog(log: SensorLogsEntity)

    @Insert
    suspend fun insertTrackingSession(session: TrackingSessionEntity)

    @Query("SELECT * FROM DailySteps ORDER BY date DESC")
    fun getAllDailyStepsFlow(): Flow<List<DailyStepsEntity>>
}
