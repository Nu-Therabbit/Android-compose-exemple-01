package com.example.proc01

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
import androidx.navigation.NavHostController
import com.example.proc01.route.Graph
import com.example.proc01.route.Screen
import com.example.proc01.ui.theme.Proc01Theme

@Composable
fun Home(navHostController: NavHostController?, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .wrapContentSize(align = Alignment.Center)
            .fillMaxWidth(0.6f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navHostController?.navigate(Graph.Animation) },
        ) {
            Text(text = "Animation")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navHostController?.navigate(Graph.Permission) },
        ) {
            Text(text = "Permission")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navHostController?.navigate(Screen.Deeplink) },
        ) {
            Text(text = "Deeplink")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navHostController?.navigate(Screen.Snackbar) },
        ) {
            Text(text = "Snackbar")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navHostController?.navigate(Screen.Battery) },
        ) {
            Text(text = "Battery Information")
        }
    }
}


@ThemePreview
@Preview(showSystemUi = true)
@OrientationPreview
@Composable
fun HomePreview() {
    Proc01Theme {
        Home(null, Modifier.fillMaxSize())
    }
}
