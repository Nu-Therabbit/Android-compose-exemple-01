package com.example.proc01.battery

import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.content.Intent
import android.os.BatteryManager
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proc01.OrientationPreview
import com.example.proc01.R
import com.example.proc01.ThemePreview
import com.example.proc01.common.SystemBroadcastReceiver
import com.example.proc01.common.animation.CircularWaveAnimation
import com.example.proc01.ui.theme.Proc01Theme
import com.example.proc01.ui.theme.chakraPetchFontFamily
import com.example.proc01.ui.theme.nunitoFamily
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun BatterInformationScreen(modifier: Modifier, context: Context? = LocalContext.current) {

    var batterInformation by remember {
        mutableStateOf(
            BatterInformation(
                level = 0,
                isCharging = false,
                temperature = 0,
                voltage = 0,
                technology = "Unknown",
                pluggedType = "Unknown",
                chargeTimeRemaining = null,
                health = "Unknown",
            ),
        )
    }
    val batteryManager = context?.getSystemService(BATTERY_SERVICE) as? BatteryManager

    SystemBroadcastReceiver(systemAction = Intent.ACTION_BATTERY_CHANGED) { result ->
        val batteryLevel = result?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val batteryPluggedType = result?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
        val batteryTemperature = result?.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1)?.div(10)
        val batteryVoltage = result?.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1)
        val batteryTechnology = result?.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY)
        val batteryHealth = result?.getIntExtra(BatteryManager.EXTRA_HEALTH, -99)
        batterInformation = BatterInformation(
            level = batteryLevel ?: 0,
            isCharging = batteryPluggedType != 0,
            temperature = batteryTemperature ?: 0,
            voltage = batteryVoltage ?: 0,
            technology = batteryTechnology ?: "Unknown",
            pluggedType = when (batteryPluggedType) {
                BatteryManager.BATTERY_PLUGGED_AC -> "AC"
                BatteryManager.BATTERY_PLUGGED_USB -> "USB"
                BatteryManager.BATTERY_PLUGGED_WIRELESS -> "Wireless"
                else -> "Unknown"
            },
            chargeTimeRemaining = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                batteryManager?.computeChargeTimeRemaining()
            } else {
                null
            },
            health = when (batteryHealth) {
                BatteryManager.BATTERY_HEALTH_COLD -> "Cold"
                BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "Unspecified Failure"
                BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "Over Voltage"
                BatteryManager.BATTERY_HEALTH_DEAD -> "Dead"
                BatteryManager.BATTERY_HEALTH_OVERHEAT -> "Overheat"
                BatteryManager.BATTERY_HEALTH_GOOD -> "Good"
                BatteryManager.BATTERY_HEALTH_UNKNOWN -> "Unknown"
                else -> "Unknown"
            },
        )
    }

    Column(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = "Battery Statu",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontFamily = nunitoFamily,
                )
                Spacer(modifier = Modifier.height(24.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularWaveAnimation(
                        size = 200.dp, waterLevel = batterInformation.level, borderWidth = 5.dp,
                    )
                    Text(
                        text = "${batterInformation.level}%",
                        style = MaterialTheme.typography.headlineLarge,
                        fontSize = 46.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = chakraPetchFontFamily,
                    )
                }


                Spacer(modifier = Modifier.height(24.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    val format = DecimalFormat("#.##")
                    format.roundingMode = RoundingMode.CEILING
                    val batterVoltage = format.format(batterInformation.voltage.toFloat() / 1000)
                    BatteryStatus(
                        modifier = Modifier.weight(0.5F),
                        name = "Voltage",
                        value = "$batterVoltage V",
                        icon = R.drawable.bolt,
                    )
                    BatteryStatus(
                        modifier = Modifier.weight(0.5F),
                        name = "Temperature",
                        description = batterInformation.health,
                        value = "${batterInformation.temperature} C",
                        icon = R.drawable.thermometer,
                    )
                }
                Row {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        BatteryStatus(
                            modifier = Modifier.weight(0.5F),
                            name = "Technology",
                            value = batterInformation.technology,
                            icon = R.drawable.technology,
                        )
                        val description = if (batterInformation.isCharging) {
                            batterInformation.chargeTimeRemaining?.let {
                                val duration = it.toDuration(DurationUnit.MILLISECONDS)
                                val format = duration.toComponents { hours, minutes, _, _ ->
                                    return@toComponents "${hours}H ${minutes}M Until full"
                                }
                                return@let format
                            }
                        } else {
                            null
                        }
                        BatteryStatus(
                            modifier = Modifier.weight(0.5F),
                            name = "Plug state",
                            description = description,
                            value = if (batterInformation.isCharging) "Plug in (${batterInformation.pluggedType})" else "Plug out",
                            icon = R.drawable.plugin,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BatteryStatus(
    modifier: Modifier,
    name: String,
    value: String,
    icon: Int,
    description: String? = null,
) {
    Row(
        modifier = modifier.padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .width(80.dp)
                .aspectRatio(1F)
                .padding(12.dp),
            painter = painterResource(icon),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondaryContainer),
            contentDescription = null,
        )
        Column {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = nunitoFamily,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = nunitoFamily,
                fontWeight = FontWeight.Medium,
            )
            if (description != null) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = nunitoFamily,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Composable
@ThemePreview
@Preview(showSystemUi = true)
@OrientationPreview
fun ButterInformationScreenPreview() {
    Proc01Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            BatterInformationScreen(modifier = Modifier.fillMaxSize(), context = null)
        }
    }
}