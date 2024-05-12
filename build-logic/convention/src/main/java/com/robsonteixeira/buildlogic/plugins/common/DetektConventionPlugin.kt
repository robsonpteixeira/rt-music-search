package com.robsonteixeira.buildlogic.plugins.common

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")
            extensions.configure<DetektExtension> {
                config.setFrom(files("$rootDir/tools/detekt/detekt-config.yml"))
            }
        }
    }
}