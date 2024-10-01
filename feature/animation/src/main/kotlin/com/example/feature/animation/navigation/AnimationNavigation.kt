package com.example.feature.animation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.feature.animation.DemoAnimationScreen
import com.example.navigation.Graph
import com.example.navigation.Screen

fun NavController.navigateToAnimation() {
    navigate(Graph.Animation)
}

fun NavGraphBuilder.registerAnimationGraph() {
    navigation<Graph.Animation>(startDestination = Screen.AnimationScreen.First) {
        composable<Screen.AnimationScreen.First> {
            DemoAnimationScreen(modifier = Modifier.fillMaxSize())
        }
    }
}