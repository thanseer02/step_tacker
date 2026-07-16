package com.antigravity.steptracker.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antigravity.steptracker.data.local.datastore.AppSettings
import com.antigravity.steptracker.domain.repository.StepRepository
import com.antigravity.steptracker.utils.StepCalculator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val stepRepository: StepRepository,
    private val appSettings: AppSettings
) : ViewModel() {

    private val todayMidnight = StepCalculator.getMidnightEpoch(System.currentTimeMillis())

    val trackingEnabled = appSettings.trackingEnabled.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    val todaySteps = stepRepository.getDailyStepsFlow(todayMidnight).map {
        it?.steps ?: 0
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        0
    )

    val dailyGoal = appSettings.dailyGoal.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        10000
    )

    fun toggleTracking(enabled: Boolean) {
        viewModelScope.launch {
            appSettings.setTrackingEnabled(enabled)
        }
    }
}
