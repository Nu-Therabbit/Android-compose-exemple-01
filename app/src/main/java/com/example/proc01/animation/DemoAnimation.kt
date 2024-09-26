package com.example.proc01.animation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proc01.OrientationPreview
import com.example.proc01.ThemePreview
import com.example.proc01.common.animation.AnimatedBorderCard
import com.example.proc01.common.animation.AnimatedCounterText
import com.example.proc01.ui.theme.Proc01Theme

@Composable
fun DemoAnimation(modifier: Modifier) {
    Column(modifier = modifier) {
        AnimatedBorderCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            gradient = Brush.sweepGradient(listOf(Color.Magenta, Color.Cyan)),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "AnimatedBorder",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam blandit mi a ex elementum, vel iaculis sapien faucibus. Maecenas volutpat, est id convallis elementum, ipsum metus dignissim purus, id varius lacus ipsum id mi.",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        AnimatedBorderCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            duration = 5000,
            borderWith = 1.dp,
            gradient = Brush.sweepGradient(listOf(Color.Red, Color.Magenta, Color.Yellow)),
        ) {
            var count by remember { mutableIntStateOf(0) }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(onClick = { count-- }) {
                    Text(text = "Decrease")
                }
                AnimatedCounterText(
                    count = count,
                    style = MaterialTheme.typography.headlineMedium,
                )
                Button(onClick = { count++ }) {
                    Text(text = "Increase")
                }
            }
        }
    }
}

@ThemePreview
@Preview(showSystemUi = true)
@OrientationPreview
@Composable
fun DemoAnimationPreview() {
    Proc01Theme {
        DemoAnimation(modifier = Modifier.fillMaxSize())
    }
}