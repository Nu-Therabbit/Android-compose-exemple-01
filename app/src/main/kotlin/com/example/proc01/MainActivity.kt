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
import androidx.navigation.compose.rememberNavController
import com.example.core.common.ObserveAsEvents
import com.example.core.designsystem.OrientationPreview
import com.example.core.designsystem.ThemePreview
import com.example.core.designsystem.theme.Proc01Theme
import com.example.navigation.NavigationAction
import com.example.navigation.Navigator
import com.example.proc01.navigation.ExampleNavHost
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

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
        val navigator = koinInject<Navigator>()
        val navController = rememberNavController()
        ObserveAsEvents(flow = navigator.navigationActions) { action ->
            when (action) {
                is NavigationAction.Navigate -> navController.navigate(
                    action.destination,
                ) {
                    action.navOptions(this)
                }

                NavigationAction.NavigateUp -> navController.navigateUp()
            }
        }
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
            ExampleNavHost(
                navController = navController,
                startDestination = navigator.startDestination,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

@Composable
@ThemePreview
@OrientationPreview
fun Proc01ThemePreview() {
    Proc01Main()
}