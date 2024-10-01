plugins {
    alias(libs.plugins.example.android.feature)
    alias(libs.plugins.example.android.library.compose)
}

android {
    namespace = "com.example.feature.battery"
}