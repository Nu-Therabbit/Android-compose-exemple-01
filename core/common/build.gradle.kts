plugins {
    alias(libs.plugins.example.android.library)
    alias(libs.plugins.example.android.library.compose)
    // for utils only
}

android {
    namespace = "com.example.core.common"
}