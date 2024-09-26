package com.example.proc01.permission

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proc01.OrientationPreview
import com.example.proc01.ThemePreview
import com.example.proc01.permission.route.PermissionScreen
import com.example.proc01.ui.theme.Proc01Theme

@Composable
fun Permission(navHostController: NavHostController?, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = { navHostController?.navigate(PermissionScreen.SinglePermission) }) {
            Text(text = "Single Permission")
        }
        Button(onClick = { navHostController?.navigate(PermissionScreen.MultiplePermission) }) {
            Text(text = "Multiple Permission")
        }
    }
}

@ThemePreview
@OrientationPreview
@Composable
fun HomePreview() {
    Proc01Theme {
        Permission(null, Modifier.fillMaxSize())
    }
}