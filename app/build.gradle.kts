@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("convention.android.application")
}

dependencies {
    implementation(project(":core:designlib"))
}