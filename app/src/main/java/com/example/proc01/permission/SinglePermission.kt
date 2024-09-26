package com.example.proc01.permission

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proc01.OrientationPreview
import com.example.proc01.ThemePreview
import com.example.proc01.ui.theme.Proc01Theme
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SinglePermission(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SinglePermissionCard(
            modifier = Modifier.fillMaxWidth(),
            permissionName = "Notification",
            permission = Manifest.permission.POST_NOTIFICATIONS,
        )
        SinglePermissionCard(
            modifier = Modifier.fillMaxWidth(),
            permissionName = "Camera",
            permission = Manifest.permission.CAMERA,
        )
    }
}

@Composable
@ThemePreview
@Preview(showSystemUi = true)
@OrientationPreview
fun SinglePermissionPreview() {
    Proc01Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SinglePermission(modifier = Modifier.fillMaxSize())
        }
    }
}