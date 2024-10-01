package com.example.core.designsystem

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class ThemePreview

@Preview(
    name = "Landscape",
    showBackground = true,
    device = Devices.AUTOMOTIVE_1024p,
    widthDp = 640,
)
@Preview(name = "Portrait", showBackground = true, device = Devices.PIXEL_4)
annotation class OrientationPreview