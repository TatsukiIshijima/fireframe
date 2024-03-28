plugins {
    alias(libs.plugins.fireframe.android.library)
    alias(libs.plugins.fireframe.android.hilt)
}

android {
    namespace = "com.tatsuki.fireframe.core.testing"
}

dependencies {
    api(kotlin("test"))

    api(libs.hilt.android.testing)
    api(libs.kotlinx.coroutines.test)
    api(libs.robolectric)

    implementation(project(":core:common"))
}