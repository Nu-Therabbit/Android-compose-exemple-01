plugins {
    alias(libs.plugins.example.android.library)
    alias(libs.plugins.example.android.library.compose)
}

android {
    namespace = "com.example.core.ui"
}

dependencies {
    implementation(project(":core:designsystem"))
}