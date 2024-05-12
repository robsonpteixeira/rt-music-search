import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.robsonteixeira.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("detekt") {
            id = "convention.detekt"
            implementationClass = "com.robsonteixeira.buildlogic.plugins.common.DetektConventionPlugin"
        }
        register("androidApp") {
            id = "convention.android.application"
            implementationClass = "com.robsonteixeira.buildlogic.plugins.AndroidAppConventionPlugin"
        }
        register("androidLib") {
            id = "convention.android.library"
            implementationClass = "com.robsonteixeira.buildlogic.plugins.AndroidLibConventionPlugin"
        }
        register("androidFeature") {
            id = "convention.android.feature"
            implementationClass = "com.robsonteixeira.buildlogic.plugins.AndroidFeatureConventionPlugin"
        }
    }
}