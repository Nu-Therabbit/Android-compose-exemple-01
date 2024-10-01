plugins {
    alias(libs.plugins.example.android.feature)
    alias(libs.plugins.example.android.library.compose)
}

android {
    namespace = "com.example.feature.permission"
}

dependencies {
    implementation(libs.accompanist.permissions)
    implementation(libs.koin)
    implementation(libs.koin.compose)
    implementation(project(":navigation"))
}