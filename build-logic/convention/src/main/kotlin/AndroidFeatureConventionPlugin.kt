import com.example.convention.implementation
import com.example.convention.library
import com.example.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("example.android.library")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            dependencies {
                implementation(libs.library("androidx-compose-navigation"))
                implementation(libs.library("kotlinx-serialization-json"))
                implementation(libs.library("androidx-lifecycle-runtime-compose"))
            }
        }
    }
}