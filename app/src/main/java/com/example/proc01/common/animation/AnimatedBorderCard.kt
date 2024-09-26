package com.example.proc01.common.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.proc01.OrientationPreview
import com.example.proc01.ThemePreview
import com.example.proc01.ui.theme.Proc01Theme

@Composable
fun AnimatedBorderCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(0.dp),
    borderWith: Dp = 2.dp,
    gradient: Brush = Brush.sweepGradient(listOf(Color.Gray, Color.White)),
    duration: Int = 10000,
    onCardClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "Infinite Color Animation")
    val degrees by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = duration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "Infinite Colors Angle",
    )

    Surface(modifier = modifier.clickable { onCardClick() }, shape = shape) {
        Surface(
            modifier = Modifier
                .clipToBounds()
                .fillMaxWidth()
                .padding(borderWith)
                .drawWithContent {
                    rotate(degrees = degrees) {
                        drawCircle(
                            brush = gradient,
                            radius = size.width,
                            blendMode = BlendMode.SrcIn,
                        )
                    }
                    drawContent()
                },
            color = MaterialTheme.colorScheme.surface,
            shape = shape,
        ) {
            content()
        }
    }
}

@ThemePreview
@Preview(showSystemUi = true)
@OrientationPreview
@Composable
fun AnimatedBorderCardPreview() {
    Proc01Theme {
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
    }
}