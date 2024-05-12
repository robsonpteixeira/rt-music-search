package com.robsonteixeira.buildlogic.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.robsonteixeira.buildlogic.config.Config
import com.robsonteixeira.buildlogic.extensions.configureAndroidCompose
import com.robsonteixeira.buildlogic.extensions.configureAndroidKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidAppConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.application")
                apply("kotlin-android")
                apply("convention.detekt")
                apply("convention.hilt")
            }
            extensions.configure<ApplicationExtension> {
                defaultConfig.apply {
                    targetSdk = Config.android.targetSdkVersion
                    minSdk = Config.android.minSdkVersion
                    compileSdk = Config.android.compileSdkVersion
                    applicationId = Config.android.applicationId
                    versionCode = Config.android.versionCode
                    versionName = Config.android.versionName
                }
                configureAndroidKotlin(this)
                configureAndroidCompose(this)
            }
        }
    }
}