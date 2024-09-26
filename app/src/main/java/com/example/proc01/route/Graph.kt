package com.example.proc01.route

import kotlinx.serialization.Serializable

sealed class Graph {
    @Serializable
    data object Home : Graph()

    @Serializable
    data object Permission : Graph()

    @Serializable
    data object Animation : Graph()
}