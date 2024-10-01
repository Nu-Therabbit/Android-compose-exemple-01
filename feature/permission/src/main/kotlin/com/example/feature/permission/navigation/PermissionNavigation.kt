package com.example.feature.permission.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.feature.permission.MultiplePermissionScreen
import com.example.feature.permission.PermissionScreen
import com.example.feature.permission.SinglePermissionScreen
import com.example.navigation.Graph
import com.example.navigation.Screen
import com.google.accompanist.permissions.ExperimentalPermissionsApi

fun NavController.navigateToPermission() {
    navigate(Graph.Permission)
}

@OptIn(ExperimentalPermissionsApi::class)
fun NavGraphBuilder.registerPermissionGraph() {
    navigation<Graph.Permission>(startDestination = Screen.PermissionScreen.First) {
        composable<Screen.PermissionScreen.First> {
            PermissionScreen(
                modifier = Modifier.fillMaxSize(),
            )
        }
        composable<Screen.PermissionScreen.SinglePermission> {
            SinglePermissionScreen(modifier = Modifier.fillMaxSize())
        }
        composable<Screen.PermissionScreen.MultiplePermission> {
            MultiplePermissionScreen(modifier = Modifier.fillMaxSize())
        }
    }
}