package com.antigravity.steptracker.ui.dashboard

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.antigravity.steptracker.service.StepTrackingService

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val trackingEnabled by viewModel.trackingEnabled.collectAsState()
    val todaySteps by viewModel.todaySteps.collectAsState()
    val dailyGoal by viewModel.dailyGoal.collectAsState()
    val context = LocalContext.current

    val permissions = buildList {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            add(Manifest.permission.ACTIVITY_RECOGNITION)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            add(Manifest.permission.POST_NOTIFICATIONS)
        }
    }.toTypedArray()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        if (result.values.all { it }) {
            viewModel.toggleTracking(true)
            StepTrackingService.start(context)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val progress by animateFloatAsState(
            targetValue = if (dailyGoal > 0) todaySteps.toFloat() / dailyGoal else 0f,
            label = "goalProgress"
        )
        
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier.size(200.dp),
            strokeWidth = 12.dp,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "$todaySteps / $dailyGoal",
            style = MaterialTheme.typography.headlineLarge
        )
        
        Text(
            text = "Steps Today",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Button(
            onClick = {
                if (!trackingEnabled) {
                    val allGranted = permissions.all {
                        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
                    }
                    if (allGranted || permissions.isEmpty()) {
                        viewModel.toggleTracking(true)
                        StepTrackingService.start(context)
                    } else {
                        launcher.launch(permissions)
                    }
                } else {
                    viewModel.toggleTracking(false)
                    StepTrackingService.stop(context)
                }
            },
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text(if (trackingEnabled) "Stop Tracking" else "Start Tracking")
        }
    }
}
