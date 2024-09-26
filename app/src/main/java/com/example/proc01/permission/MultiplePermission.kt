package com.example.proc01.permission

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.proc01.OrientationPreview
import com.example.proc01.ThemePreview
import com.example.proc01.ui.theme.Proc01Theme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun customRememberMultiplePermissionsState(
    permissions: List<String>,
    fakeStatusList: Map<String, PermissionStatus>,
    onPermissionsResult: (Map<String, Boolean>) -> Unit = {},
): MultiplePermissionsState {
    val isInspection = LocalInspectionMode.current
    return if (isInspection) {
        object : MultiplePermissionsState {
            override val allPermissionsGranted: Boolean
                get() = fakeStatusList.any { it.value is PermissionStatus.Granted }
            override val permissions: List<PermissionState>
                get() = fakeStatusList.map { per ->
                    object : PermissionState {
                        override val permission: String
                            get() = per.key
                        override val status: PermissionStatus
                            get() = per.value

                        override fun launchPermissionRequest() = Unit
                    }
                }
            override val revokedPermissions: List<PermissionState>
                get() = emptyList()
            override val shouldShowRationale: Boolean
                get() = false

            override fun launchMultiplePermissionRequest() = Unit

        }
    } else {
        rememberMultiplePermissionsState(
            permissions = permissions,
            onPermissionsResult = onPermissionsResult,
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MultiplePermission(
    modifier: Modifier = Modifier,
    permissions: List<String> = listOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.BLUETOOTH,
    ),
    fakeStatusList: Map<String, PermissionStatus> = emptyMap(),
) {
    val multiplePermissionState =
        customRememberMultiplePermissionsState(
            permissions = permissions,
            fakeStatusList = fakeStatusList,
        )
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START) {
                    multiplePermissionState.launchMultiplePermissionRequest()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        },
    )
    Box(modifier = modifier.padding(16.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Media file", style = MaterialTheme.typography.headlineMedium)
                permissions.forEach { permission ->
                    val status =
                        multiplePermissionState.permissions.firstOrNull { it.permission == permission }?.status
                            ?: return@Column
                    PermissionLabel(name = permission, status = status)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Button(onClick = { multiplePermissionState.launchMultiplePermissionRequest() }) {
                    Text(text = "Request permission")
                }
            }
        }
    }

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionLabel(name: String, status: PermissionStatus) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = name, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = getStatus(status), style = MaterialTheme.typography.bodyMedium)
    }
}

@OptIn(ExperimentalPermissionsApi::class)
private fun getStatus(status: PermissionStatus): String {
    return when (status) {
        is PermissionStatus.Granted -> {
            "Granted"
        }

        is PermissionStatus.Denied -> {
            if (status.shouldShowRationale) {
                return "Denied Rationale"
            }
            "Denied"
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
@ThemePreview
@Preview(showSystemUi = true)
@OrientationPreview
fun MultiplePermissionPreview() {
    Proc01Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MultiplePermission(
                modifier = Modifier.fillMaxWidth(),
                permissions = listOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.BLUETOOTH,
                ),
                fakeStatusList = mapOf(
                    Manifest.permission.CAMERA to PermissionStatus.Granted,
                    Manifest.permission.RECORD_AUDIO to PermissionStatus.Denied(false),
                    Manifest.permission.BLUETOOTH to PermissionStatus.Denied(true),
                ),
            )
        }
    }
}