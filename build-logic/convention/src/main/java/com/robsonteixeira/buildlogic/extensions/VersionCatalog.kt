package com.robsonteixeira.buildlogic.extensions

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.versionCatalog
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

