package com.robsonteixeira.buildlogic.extensions

import com.android.build.api.dsl.CommonExtension
import com.robsonteixeira.buildlogic.config.Config
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureAndroidKotlin(
    extension: CommonExtension<*, *, *, *, *, *>,
) {
    with(extension) {
        namespace = Config.android.nameSpace
        compileSdk = Config.android.compileSdkVersion
        defaultConfig.apply {
            minSdk = Config.android.minSdkVersion
            testInstrumentationRunner = "androidx.test.runner.AndroidJunitRunner"
            vectorDrawables.useSupportLibrary = true
        }
        compileOptions {
            sourceCompatibility = Config.jvm.javaVersion
            targetCompatibility = Config.jvm.javaVersion
        }
        packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"

        dependencies {
            add("implementation", versionCatalog.findLibrary("core-ktx").get())
            add("implementation", versionCatalog.findLibrary("lifecycle-runtime-ktx").get())
            add("testImplementation", versionCatalog.findLibrary("junit").get())
            add("androidTestImplementation", versionCatalog.findLibrary("androidx-test-ext-junit").get())
            add("androidTestImplementation", versionCatalog.findLibrary("espresso-core").get())
        }
    }
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = Config.jvm.kotlinJvm
            freeCompilerArgs = freeCompilerArgs + Config.jvm.freeCompilerArgs
        }
    }
}