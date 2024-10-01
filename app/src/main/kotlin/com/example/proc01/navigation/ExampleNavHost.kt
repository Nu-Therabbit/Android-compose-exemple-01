package com.example.proc01.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feature.animation.navigation.navigateToAnimation
import com.example.feature.animation.navigation.registerAnimationGraph
import com.example.feature.battery.navigation.navigateToBattery
import com.example.feature.battery.navigation.registerBatteryGraph
import com.example.feature.home.navigation.registerHomeGraph
import com.example.feature.permission.navigation.navigateToPermission
import com.example.feature.permission.navigation.registerPermissionGraph
import com.example.navigation.Destination
import com.example.navigation.Screen

@Composable
fun ExampleNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        registerHomeGraph(
            onAnimationClick = navController::navigateToAnimation,
            onBatteryClick = navController::navigateToBattery,
            onPermissionClick = navController::navigateToPermission,
        )
        registerAnimationGraph()
        registerBatteryGraph()
        registerPermissionGraph()
        composable<Screen.Deeplink> {

        }
        composable<Screen.Snackbar> {

        }
    }
}