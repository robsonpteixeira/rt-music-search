package com.robsonteixeira.buildlogic.plugins

import com.android.build.api.dsl.LibraryExtension
import com.robsonteixeira.buildlogic.extensions.configureAndroidCompose
import com.robsonteixeira.buildlogic.extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("convention.android.library")
                apply("convention.hilt")
            }
            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
                dependencies {
                    add("implementation", versionCatalog.findLibrary("hilt-navigation-compose").get())
                    add("implementation", versionCatalog.findLibrary("retrofit-main").get())
                    add("implementation", versionCatalog.findLibrary("moshi-main").get())
                    add("kapt", versionCatalog.findLibrary("moshi-codegen").get())
                    add("implementation", versionCatalog.findLibrary("coroutines").get())
                    add("implementation", versionCatalog.findLibrary("collections-immutable").get())
                    add("testImplementation", versionCatalog.findLibrary("androidx-arch-test").get())
                    add("testImplementation", versionCatalog.findLibrary("coroutines-test").get())
                    add("testImplementation", versionCatalog.findLibrary("turbine").get())
                    add("testImplementation", versionCatalog.findLibrary("mockk").get())
                }
            }
        }
    }
}