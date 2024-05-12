package com.robsonteixeira.buildlogic.plugins

import com.android.build.api.dsl.LibraryExtension
import com.robsonteixeira.buildlogic.extensions.configureAndroidKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.library")
                apply("kotlin-android")
                apply("convention.detekt")
            }
            extensions.configure<LibraryExtension> {
                configureAndroidKotlin(this)
            }
        }
    }
}