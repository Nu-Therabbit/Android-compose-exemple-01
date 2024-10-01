plugins {
    alias(libs.plugins.example.android.library)
    alias(libs.plugins.example.android.library.compose)
}

android {
    namespace = "com.example.core.designsystem"
}

dependencies {
    implementation(libs.androidx.ui.google.fonts)
}