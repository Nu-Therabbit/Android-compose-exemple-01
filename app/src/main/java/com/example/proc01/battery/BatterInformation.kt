package com.example.proc01.battery

data class BatterInformation(
    val level: Int,
    val isCharging: Boolean,
    val temperature: Int,
    val voltage: Int,
    val technology: String,
    val pluggedType: String,
    val chargeTimeRemaining: Long?,
    val health: String,
)
