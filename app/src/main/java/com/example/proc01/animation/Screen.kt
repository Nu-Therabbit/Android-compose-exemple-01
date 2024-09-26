package com.example.proc01.animation

import kotlinx.serialization.Serializable

@Serializable
sealed class AnimationScreen {
    @Serializable
    data object First : AnimationScreen()
}