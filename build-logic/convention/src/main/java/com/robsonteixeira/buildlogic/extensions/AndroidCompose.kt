package com.robsonteixeira.buildlogic.extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    extension: CommonExtension<*, *, *, *, *, *>,
) {
    with(extension) {
        buildFeatures.compose = true
        composeOptions.kotlinCompilerExtensionVersion =
            versionCatalog.findVersion("androidx-compose-compiler").get().toString()

        dependencies {
            add("implementation", versionCatalog.findLibrary("activity-compose").get())
            add("implementation", platform(versionCatalog.findLibrary("compose-bom").get()))
            add("implementation", versionCatalog.findBundle("compose").get())
            add("androidTestImplementation", platform(versionCatalog.findLibrary("compose-bom").get()))
            add("testImplementation", versionCatalog.findLibrary("ui-test-junit4").get())
            add("debugImplementation", versionCatalog.findLibrary("ui-tooling").get())
            add("debugImplementation", versionCatalog.findLibrary("ui-test-manifest").get())
        }
    }
}