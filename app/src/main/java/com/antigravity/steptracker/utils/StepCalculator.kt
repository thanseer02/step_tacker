package com.antigravity.steptracker.utils

import java.util.Calendar

object StepCalculator {

    fun isNewDay(lastUpdateTime: Long, currentTime: Long): Boolean {
        val lastCal = Calendar.getInstance().apply { timeInMillis = lastUpdateTime }
        val currCal = Calendar.getInstance().apply { timeInMillis = currentTime }
        return lastCal.get(Calendar.YEAR) != currCal.get(Calendar.YEAR) ||
                lastCal.get(Calendar.DAY_OF_YEAR) != currCal.get(Calendar.DAY_OF_YEAR)
    }

    fun getMidnightEpoch(time: Long): Long {
        val cal = Calendar.getInstance().apply {
            timeInMillis = time
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return cal.timeInMillis
    }
}
