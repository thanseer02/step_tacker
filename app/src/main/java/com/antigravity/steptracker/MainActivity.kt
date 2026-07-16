package com.antigravity.steptracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.antigravity.steptracker.ui.theme.StepTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.antigravity.steptracker.ui.dashboard.DashboardScreen
import com.antigravity.steptracker.ui.history.HistoryScreen
import com.antigravity.steptracker.ui.settings.SettingsScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StepTrackerTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = currentRoute == "dashboard",
                                onClick = { navController.navigate("dashboard") { launchSingleTop = true } },
                                icon = { Text("🏠") },
                                label = { Text("Home") }
                            )
                            NavigationBarItem(
                                selected = currentRoute == "history",
                                onClick = { navController.navigate("history") { launchSingleTop = true } },
                                icon = { Text("📜") },
                                label = { Text("History") }
                            )
                            NavigationBarItem(
                                selected = currentRoute == "settings",
                                onClick = { navController.navigate("settings") { launchSingleTop = true } },
                                icon = { Text("⚙️") },
                                label = { Text("Settings") }
                            )
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "dashboard",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("dashboard") { DashboardScreen() }
                        composable("history") { HistoryScreen() }
                        composable("settings") { SettingsScreen() }
                    }
                }
            }
        }
    }
}
