package com.example.feature.permission

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
import com.example.core.designsystem.OrientationPreview
import com.example.core.designsystem.ThemePreview
import com.example.core.designsystem.theme.Proc01Theme
import org.koin.androidx.compose.koinViewModel

@Composable
fun PermissionScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<PermissionViewModel>()
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = {
                viewModel.navigateToSingleScreen()
            },
        ) {
            Text(text = "Single Permission")
        }
        Button(
            onClick = {
                viewModel.navigateToMultipleScreen()
            },
        ) {
            Text(text = "Multiple Permission")
        }
    }
}

@ThemePreview
@OrientationPreview
@Composable
fun HomePreview() {
    Proc01Theme {
        PermissionScreen(Modifier.fillMaxSize())
    }
}