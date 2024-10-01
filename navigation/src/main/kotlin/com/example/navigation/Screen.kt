package com.example.navigation

import kotlinx.serialization.Serializable

sealed interface Screen : Destination {
    @Serializable
    data object Home : Screen

    @Serializable
    data object Deeplink : Screen

    @Serializable
    data object Snackbar : Screen

    @Serializable
    data object Battery : Screen

    @Serializable
    sealed class PermissionScreen : Screen {
        @Serializable
        data object First : PermissionScreen()

        @Serializable
        data object SinglePermission : PermissionScreen()

        @Serializable
        data object MultiplePermission : PermissionScreen()
    }

    @Serializable
    sealed class AnimationScreen {
        @Serializable
        data object First : AnimationScreen()
    }
}
