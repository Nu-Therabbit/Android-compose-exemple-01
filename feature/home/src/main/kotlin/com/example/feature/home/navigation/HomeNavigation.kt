package com.example.feature.home.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.feature.home.HomeScreen
import com.example.navigation.Graph
import com.example.navigation.Screen

fun NavGraphBuilder.registerHomeGraph(
    onAnimationClick: () -> Unit,
    onBatteryClick: () -> Unit,
    onPermissionClick: () -> Unit,
) {
    navigation<Graph.Home>(startDestination = Screen.Home) {
        composable<Screen.Home> {
            HomeScreen(
                onAnimationClick = onAnimationClick,
                onBatteryClick = onBatteryClick,
                onPermissionClick = onPermissionClick,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}