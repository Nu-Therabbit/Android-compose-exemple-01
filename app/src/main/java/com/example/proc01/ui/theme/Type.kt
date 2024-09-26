package com.example.proc01.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.proc01.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs,
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),
)

val nunitoFamily = FontFamily(
    Font(R.font.nunito_black, FontWeight.Bold),
    Font(R.font.nunito_medium, FontWeight.Medium),
)

val chakraPetchFontName = GoogleFont("Chakra Petch")

val chakraPetchFontFamily = FontFamily(
    Font(googleFont = chakraPetchFontName, fontProvider = provider),
    Font(
        googleFont = chakraPetchFontName,
        fontProvider = provider,
        weight = FontWeight.Bold,
    ),
    Font(
        googleFont = chakraPetchFontName,
        fontProvider = provider,
        weight = FontWeight.Medium,
    ),
)


