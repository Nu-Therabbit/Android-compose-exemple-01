package com.example.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.designsystem.OrientationPreview
import com.example.core.designsystem.ThemePreview
import com.example.core.designsystem.theme.Proc01Theme

@Composable
fun HomeScreen(
    onAnimationClick: () -> Unit,
    onBatteryClick: () -> Unit,
    onPermissionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .wrapContentSize(align = Alignment.Center)
            .fillMaxWidth(0.6f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onAnimationClick,
        ) {
            Text(text = "Animation")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onBatteryClick,
        ) {
            Text(text = "Battery Information")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onPermissionClick,
        ) {
            Text(text = "Permission")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { },
        ) {
            Text(text = "Deeplink")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { },
        ) {
            Text(text = "Snackbar")
        }
    }
}


@ThemePreview
@Preview(showSystemUi = true)
@OrientationPreview
@Composable
fun HomePreview() {
    Proc01Theme {
        HomeScreen(
            onAnimationClick = {},
            onBatteryClick = {},
            onPermissionClick = {},
            Modifier.fillMaxSize(),
        )
    }
}
