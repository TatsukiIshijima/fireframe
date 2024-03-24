plugins {
    alias(libs.plugins.fireframe.android.library)
    alias(libs.plugins.fireframe.android.hilt)
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "com.tatsuki.fireframe.core.network"
}

dependencies {
    api(project(":core:common"))

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}