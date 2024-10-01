plugins {
    alias(libs.plugins.example.android.feature)
    alias(libs.plugins.example.android.library.compose)
}

android {
    namespace = "com.example.feature.battery"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":navigation"))

    implementation(libs.koin)
    implementation(libs.koin.compose)
}