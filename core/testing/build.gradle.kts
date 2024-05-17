plugins {
    alias(libs.plugins.fireframe.android.library)
    alias(libs.plugins.fireframe.android.hilt)
}

android {
    namespace = "com.tatsuki.fireframe.core.testing"
}

dependencies {
    api(kotlin("test"))
    api(libs.androidx.compose.ui.test)
    api(libs.hilt.android.testing)
    api(libs.kotlinx.coroutines.test)
    api(libs.robolectric)
    api(libs.turbine)

    api(libs.androidx.ui.test.manifest)

    implementation(project(":core:common"))
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.roborazzi)
}