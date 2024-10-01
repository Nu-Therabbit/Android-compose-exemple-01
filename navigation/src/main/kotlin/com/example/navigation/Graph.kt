package com.example.navigation

import kotlinx.serialization.Serializable

sealed interface Graph : Destination {
    @Serializable
    data object Home : Graph

    @Serializable
    data object Permission : Graph

    @Serializable
    data object Animation : Graph
}