plugins {
    alias(libs.plugins.example.android.library)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.example.navigation"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.koin)
    implementation(libs.koin.compose)
}