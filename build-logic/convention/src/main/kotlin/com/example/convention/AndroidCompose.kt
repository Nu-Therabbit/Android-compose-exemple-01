package com.example.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures.compose = true
        composeOptions.kotlinCompilerExtensionVersion =
            libs.versionString("composeCompiler")

        dependencies {
            // https://developer.android.com/develop/ui/compose/setup
            implementation(platform(libs.library("androidx-compose-bom")))
            androidTestImplementation(platform(libs.library("androidx-compose-bom")))
            implementation(libs.library("androidx-material3"))

            // Android Studio Preview support
            implementation(libs.library("androidx-ui-tooling-preview"))
            debugImplementation(libs.library("androidx-ui-tooling"))

            // UI Tests
            androidTestImplementation(libs.library("androidx-ui-test-junit4"))
            debugImplementation(libs.library("androidx-ui-test-manifest"))
        }
    }
}