pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Proc01"
include(":app")
include(":core:common")
include(":feature:battery")
include(":core:designsystem")
include(":core:model")
include(":core:ui")
include(":feature:home")
include(":feature:animation")
include(":feature:permission")
include(":navigation")
