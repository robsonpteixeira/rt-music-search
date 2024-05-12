package com.robsonteixeira.buildlogic.plugins

import com.android.build.api.dsl.LibraryExtension
import com.robsonteixeira.buildlogic.extensions.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("convention.android.library")
            }
            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
            }
        }
    }
}