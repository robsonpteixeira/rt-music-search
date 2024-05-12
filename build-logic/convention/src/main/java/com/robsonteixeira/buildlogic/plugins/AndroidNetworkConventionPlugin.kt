package com.robsonteixeira.buildlogic.plugins

import com.android.build.api.dsl.LibraryExtension
import com.robsonteixeira.buildlogic.extensions.configureAndroidCompose
import com.robsonteixeira.buildlogic.extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

private const val s = "retrofit-main"

class AndroidNetworkConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("convention.android.library")
                apply("convention.hilt")
            }
            extensions.configure<LibraryExtension> {
                dependencies {
                    add("implementation", versionCatalog.findBundle("network").get())
                }
            }
        }
    }
}