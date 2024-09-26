package com.example.proc01.common.animation

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.translate
import com.example.proc01.ThemePreview
import com.example.proc01.ui.theme.Proc01Theme
import kotlin.math.PI
import kotlin.math.sin

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun WavesLevelIndicator(
    modifier: Modifier,
    progress: Int,
    wavesAmplitude: Float,
    wavesColor: Color,
) {
    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.Center) {
        AnimatedPathTranslation(progress, wavesAmplitude, wavesColor)
    }
}

@Composable
fun AnimatedPathTranslation(
    progress: Int,
    wavesAmplitude: Float,
    wavesColor: Color,
) {
    val transition = rememberInfiniteTransition(label = "AnimatedPathTranslation")

    val waveShiftRatio = transition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = WavesShiftAnimationDurationInMillis, easing = LinearEasing,
            ),
        ),
        label = "waveShiftRatio",
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val wavePath = createPath(
            width = canvasWidth.toInt(),
            height = canvasHeight.toInt(),
            waveShiftRatio = waveShiftRatio.value,
            progress = progress,
            wavesAmplitude = wavesAmplitude,
        )
        drawPath(
            color = (wavesColor.copy(alpha = 0.6f)),
            path = wavePath[0],
        )

        translate(0f, 0f) {
            drawPath(
                color = (wavesColor), path = wavePath[1],
            )
        }
    }
}

private fun createPath(
    width: Int,
    height: Int,
    waveShiftRatio: Float,
    progress: Int,
    wavesAmplitude: Float,
): List<Path> {
    val angularFrequency = 2f * PI / width
    val amplitude = height * wavesAmplitude
    val waterLevel = height - (height * progress.toFloat() / 100F)
    //(height / progress * 10).toFloat()
    // height * (progress.toFloat() / 100F)

    val originalPath = Path().apply {
        val startX = 0f

        moveTo(startX, waterLevel)

        for (x in 0..width) {
            val wx = x * angularFrequency
            val y = waterLevel + amplitude * sin(wx + waveShiftRatio * 2 * PI).toFloat()
            lineTo(x.toFloat(), y)
        }
        lineTo(width.toFloat(), height.toFloat())
        lineTo(startX, height.toFloat())
        close()
    }

    val path2 = Path().apply {
        val startX = 0f

        moveTo(startX, waterLevel)

        for (x in 0..width) {
            val wx = x * angularFrequency + 1.3
            val y = waterLevel + amplitude * sin(wx + waveShiftRatio * 2 * PI).toFloat()
            lineTo(x.toFloat(), y)
        }
        lineTo(width.toFloat(), height.toFloat())
        lineTo(startX, height.toFloat())
        close()
    }

    return listOf(originalPath, path2)
}

private const val WavesShiftAnimationDurationInMillis = 2500

@Composable
@ThemePreview
fun WavesLoadingIndicatorPreview() {
    val waterLevel = remember { mutableIntStateOf(40) }
    Proc01Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            WavesLevelIndicator(
                modifier = Modifier.fillMaxWidth(),
                wavesAmplitude = 0.03F,
                wavesColor = Color.Blue,
                progress = waterLevel.value,
            )
        }
    }
}