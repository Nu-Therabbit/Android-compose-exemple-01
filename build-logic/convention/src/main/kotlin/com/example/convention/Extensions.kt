/*
 * Copyright 2023 The Android Open Source Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.example.convention

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.provider.Provider
import org.gradle.internal.Cast.uncheckedCast
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo
import org.gradle.kotlin.dsl.add
import org.gradle.kotlin.dsl.getByType

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun VersionCatalog.versionInt(alias: String): Int = findVersion(alias).get().toString().toInt()
fun VersionCatalog.versionString(alias: String): String = findVersion(alias).get().toString()
fun VersionCatalog.library(alias: String): Provider<MinimalExternalModuleDependency> =
    this.findLibrary(alias).get()

fun DependencyHandler.implementations(dependencyNotations: List<String>): List<Dependency?> =
    dependencyNotations.map {
        add("implementation", it)
    }

fun DependencyHandler.`implementation`(dependencyNotation: Any): Dependency? = add(
    "implementation", dependencyNotation,
)

fun DependencyHandler.`implementation`(
    dependencyNotation: String,
    dependencyConfiguration: Action<ExternalModuleDependency>,
): ExternalModuleDependency = addDependencyTo(
    dependencies = this,
    configuration = "implementation",
    dependencyNotation = dependencyNotation,
    configurationAction = dependencyConfiguration,
)

fun DependencyHandler.`androidTestImplementation`(dependencyNotation: Any): Dependency? = add(
    "androidTestImplementation", dependencyNotation,
)

fun <T : ModuleDependency> DependencyHandler.`androidTestImplementation`(
    dependency: T,
    dependencyConfiguration: T.() -> Unit,
): T = add("androidTestImplementation", dependency, dependencyConfiguration)

fun DependencyHandler.`testImplementation`(dependencyNotation: Any): Dependency? = add(
    "testImplementation", dependencyNotation,
)

fun <T : ModuleDependency> DependencyHandler.`testImplementation`(
    dependency: T,
    dependencyConfiguration: T.() -> Unit,
): T = add("testImplementation", dependency, dependencyConfiguration)

fun DependencyHandler.`debugImplementation`(dependencyNotation: Any): Dependency? = add(
    "debugImplementation", dependencyNotation,
)

fun DependencyHandler.`debugImplementation`(
    dependencyNotation: String,
    dependencyConfiguration: Action<ExternalModuleDependency>,
): ExternalModuleDependency = addDependencyTo(
    dependencies = this,
    configuration = "debugImplementation",
    dependencyNotation = dependencyNotation,
    configurationAction = dependencyConfiguration,
)

fun <T : ModuleDependency> DependencyHandler.`debugImplementation`(
    dependency: T,
    dependencyConfiguration: T.() -> Unit,
): T = add("debugImplementation", dependency, dependencyConfiguration)

fun DependencyHandler.`coreLibraryDesugaring`(dependencyNotation: Any): Dependency? = add(
    "coreLibraryDesugaring", dependencyNotation,
)

fun DependencyHandler.`testRuntimeOnly`(dependencyNotation: Any): Dependency? = add(
    "testRuntimeOnly", dependencyNotation,
)

fun DependencyHandler.`testCompileOnly`(dependencyNotation: Any): Dependency? = add(
    "testCompileOnly", dependencyNotation,
)

fun DependencyHandler.`compileOnly`(dependencyNotation: Any): Dependency? = add(
    "compileOnly", dependencyNotation,
)

fun DependencyHandler.project(
    path: String,
    configuration: String? = null,
): ProjectDependency = uncheckedCast(
    project(
        if (configuration != null) mapOf("path" to path, "configuration" to configuration)
        else mapOf("path" to path),
    ),
)!!