package com.antigravity.steptracker.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antigravity.steptracker.data.local.datastore.AppSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appSettings: AppSettings
) : ViewModel() {

    val dailyGoal = appSettings.dailyGoal.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        10000
    )

    fun updateDailyGoal(goal: Int) {
        viewModelScope.launch {
            appSettings.setDailyGoal(goal)
        }
    }
}
