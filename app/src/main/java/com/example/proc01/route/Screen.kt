package com.example.proc01.route

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Home : Screen()

    @Serializable
    data object Deeplink : Screen()

    @Serializable
    data object Snackbar : Screen()

    @Serializable
    data object Battery : Screen()
}
