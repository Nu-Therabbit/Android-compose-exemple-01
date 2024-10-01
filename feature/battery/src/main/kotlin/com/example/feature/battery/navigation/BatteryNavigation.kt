package com.example.feature.battery.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.feature.battery.BatterInformationScreen
import com.example.navigation.Screen

fun NavController.navigateToBattery() {
    navigate(Screen.Battery)
}

fun NavGraphBuilder.registerBatteryGraph() {
    composable<Screen.Battery> {
        BatterInformationScreen(modifier = Modifier.fillMaxSize())
    }
}