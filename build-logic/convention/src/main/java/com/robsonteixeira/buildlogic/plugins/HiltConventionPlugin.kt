package com.robsonteixeira.buildlogic.plugins

import com.robsonteixeira.buildlogic.extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.dagger.hilt.android")
                apply("org.jetbrains.kotlin.kapt")
            }

            dependencies {
                add("implementation", versionCatalog.findLibrary("hilt-main").get())
                add("kapt", versionCatalog.findLibrary("hilt-compiler").get())
                add("kaptAndroidTest", versionCatalog.findLibrary("hilt-test-compiler").get())
            }
        }
    }
}