package com.example.core.ui.animation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.designsystem.OrientationPreview
import com.example.core.designsystem.ThemePreview
import com.example.core.designsystem.theme.Proc01Theme

/**
 * ref1 https://github.com/lopspower/CircularFillableLoaders
 * ref2 https://github.com/SEAbdulbasit/circular-fillable-loaders
 * ref3 https://proandroiddev.com/jetpack-compose-tutorial-replicating-the-water-level-widget-4ae29792f852
 * */
@Composable
fun CircularWaveAnimation(
    size: Dp = 300.dp,
    waterLevel: Int,
    backgroundImage: ImageBitmap? = null,
    wavesAmplitude: Float = 0.05F,
    wavesColor: Color = MaterialTheme.colorScheme.primary,
    borderWidth: Dp? = null,
    borderColor: Color? = null,
) {
    Box(
        modifier = Modifier
            .size(size)
            .aspectRatio(1F)
            .clip(CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
        ) {
            drawCircle(
                color = Color.Transparent,
                radius = this.size.width.dp.toPx(),
            )
        }
        if (backgroundImage != null) {
            Image(
                bitmap = backgroundImage,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop,
            )
        }
        WavesLevelIndicator(
            modifier = Modifier.fillMaxSize(),
            progress = waterLevel,
            wavesAmplitude = wavesAmplitude,
            wavesColor = wavesColor,
        )
        if (borderWidth != null) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    brush = SolidColor(borderColor ?: wavesColor),
                    radius = (this.size.width) / 2f,
                    style = Stroke(width = borderWidth.toPx()),
                )
            }
        }
    }
}

@Composable
@ThemePreview
@Preview(showSystemUi = true)
@OrientationPreview
fun CircularFillAbleLoaderPreview() {
    var waterLevel by remember { mutableIntStateOf(30) }
    Proc01Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column {
                CircularWaveAnimation(
                    waterLevel = waterLevel,
                    backgroundImage = null,
                    wavesAmplitude = 0.05F,
                    wavesColor = MaterialTheme.colorScheme.primary,
                    borderWidth = 5.dp,
                )
                Slider(
                    value = waterLevel.toFloat(),
                    onValueChange = {
                        waterLevel = (it * 100).toInt()
                    },
                )
            }
        }
    }
}