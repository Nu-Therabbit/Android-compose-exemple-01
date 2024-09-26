package com.example.proc01.permission.route

import kotlinx.serialization.Serializable

@Serializable
sealed class PermissionScreen {
    @Serializable
    data object First : PermissionScreen()

    @Serializable
    data object SinglePermission : PermissionScreen()

    @Serializable
    data object MultiplePermission : PermissionScreen()
}
