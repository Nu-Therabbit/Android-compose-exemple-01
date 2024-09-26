package com.example.proc01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.proc01.animation.AnimationScreen
import com.example.proc01.animation.DemoAnimation
import com.example.proc01.battery.BatterInformationScreen
import com.example.proc01.permission.MultiplePermission
import com.example.proc01.permission.Permission
import com.example.proc01.permission.SinglePermission
import com.example.proc01.permission.route.PermissionScreen
import com.example.proc01.route.Graph
import com.example.proc01.route.Screen
import com.example.proc01.ui.theme.Proc01Theme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.launch

const val DEEPLINK_DOMAIN = "coding.com"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proc01Main()
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Proc01Main() {
    Proc01Theme {
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        ObserveAsEvents(flow = SnackbarController.events, snackbarHostState) { event ->
            scope.launch {
                snackbarHostState.currentSnackbarData?.dismiss()

                val result = snackbarHostState.showSnackbar(
                    message = event.message,
                    actionLabel = event.action?.name,
                    duration = SnackbarDuration.Long,
                )

                if (result == SnackbarResult.ActionPerformed) {
                    event.action?.action?.invoke()
                }
            }
        }
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Graph.Home,
                modifier = Modifier.padding(innerPadding),
            ) {
                navigation<Graph.Home>(startDestination = Screen.Home) {
                    composable<Screen.Home> {
                        Home(
                            navHostController = navController,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
                navigation<Graph.Permission>(startDestination = PermissionScreen.First) {
                    composable<PermissionScreen.First> {
                        Permission(
                            navHostController = navController,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                    composable<PermissionScreen.SinglePermission> {
                        SinglePermission(modifier = Modifier.fillMaxSize())
                    }
                    composable<PermissionScreen.MultiplePermission> {
                        MultiplePermission(modifier = Modifier.fillMaxSize())
                    }
                }
                navigation<Graph.Animation>(startDestination = AnimationScreen.First) {
                    composable<AnimationScreen.First> {
                        DemoAnimation(modifier = Modifier.fillMaxSize())
                    }
                }
                composable<Screen.Battery> {
                    BatterInformationScreen(modifier = Modifier.fillMaxSize())
                }
                composable<Screen.Deeplink> {

                }
                composable<Screen.Snackbar> {

                }
            }
        }
    }
}

@Composable
@ThemePreview
@OrientationPreview
fun Proc01ThemePreview() {
    Proc01Main()
}