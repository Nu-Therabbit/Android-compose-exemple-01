package com.example.feature.permission

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.designsystem.OrientationPreview
import com.example.core.designsystem.ThemePreview
import com.example.core.designsystem.theme.Proc01Theme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun customRememberPermissionsState(
    permission: String,
    fakeStatus: PermissionStatus,
): PermissionState {
    val isInspection = LocalInspectionMode.current
    return if (isInspection) {
        object : PermissionState {
            override val permission: String
                get() = permission
            override val status: PermissionStatus
                get() = fakeStatus

            override fun launchPermissionRequest() = Unit
        }
    } else {
        rememberPermissionState(permission = permission)
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SinglePermissionCard(
    modifier: Modifier = Modifier,
    permissionName: String,
    permission: String,
    fakeStatus: PermissionStatus = PermissionStatus.Granted,
) {
    val permissionState =
        customRememberPermissionsState(permission = permission, fakeStatus = fakeStatus)
    var isEnable by remember { mutableStateOf(false) }
    var permissionStatusText by remember { mutableStateOf("Unknown") }

    LaunchedEffect(permissionState.status) {
        // Can not check permanently declined or init.
        when (val status = permissionState.status) {
            is PermissionStatus.Granted -> {
                isEnable = false
                permissionStatusText = "Granted"
            }

            is PermissionStatus.Denied -> {
                isEnable = true
                if (status.shouldShowRationale) {
                    permissionStatusText = "Denied Rationale"
                    return@LaunchedEffect
                }
                permissionStatusText = "Denied"
            }
        }
    }
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Column(
            modifier = modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = permissionName, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = permissionStatusText, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.weight(1F))
                Switch(
                    checked = permissionState.status.isGranted,
                    onCheckedChange = {
                        permissionState.launchPermissionRequest()
                    },
                    enabled = isEnable,
                )
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
@ThemePreview
@OrientationPreview
fun SinglePermissionCardPreview() {
    Proc01Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SinglePermissionCard(
                modifier = Modifier.fillMaxWidth(),
                permissionName = "Camera",
                permission = Manifest.permission.CAMERA,
                fakeStatus = PermissionStatus.Granted,
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
@Preview
fun SinglePermissionCardDeniedPreview() {
    Proc01Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SinglePermissionCard(
                modifier = Modifier.fillMaxWidth(),
                permissionName = "Camera",
                permission = Manifest.permission.CAMERA,
                fakeStatus = PermissionStatus.Denied(shouldShowRationale = false),
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
@Preview
fun SinglePermissionCardDeniedRationalePreview() {
    Proc01Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SinglePermissionCard(
                modifier = Modifier.fillMaxWidth(),
                permissionName = "Camera",
                permission = Manifest.permission.CAMERA,
                fakeStatus = PermissionStatus.Denied(shouldShowRationale = true),
            )
        }
    }
}