package com.example.proc01

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScreenA(
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ScreenAViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val scope = rememberCoroutineScope()

    val notificationPermission =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (notificationPermission.status.isGranted) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            SnackbarController.sendEvent(
                                event = SnackbarEvent(
                                    message = "Hello world!",
                                ),
                            )
                        }
                    },
                ) {
                    Text(text = "Show snackbar")
                }
                Button(
                    onClick = {
                        viewModel.showSnackbar()
                    },
                ) {
                    Text(text = "Show snackbar with action")
                }
                Button(onClick = onNavigate) {
                    Text(text = "Navigate to B")
                }
            }
        } else {
            val textToShow = if (notificationPermission.status.shouldShowRationale) {
                "Request permission rationale"
            } else {
                "Request permission"
            }
            Button(onClick = { notificationPermission.launchPermissionRequest() }) {
                Text(textToShow)
            }
        }
    }
}