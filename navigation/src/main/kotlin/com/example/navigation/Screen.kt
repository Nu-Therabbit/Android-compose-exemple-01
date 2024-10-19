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

    sealed interface PermissionScreen : Screen {
        @Serializable
        data object First : PermissionScreen

        @Serializable
        data object SinglePermission : PermissionScreen

        @Serializable
        data object MultiplePermission : PermissionScreen
    }

    sealed interface AnimationScreen : Screen {
        @Serializable
        data object First : AnimationScreen
    }
}
