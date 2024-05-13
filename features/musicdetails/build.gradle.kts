plugins {
    id("convention.android.feature")
}

dependencies {
    implementation(project(":core:analytics"))
    implementation(project(":core:designlib"))
    implementation(project(":core:network"))

    implementation(libs.coil)
}