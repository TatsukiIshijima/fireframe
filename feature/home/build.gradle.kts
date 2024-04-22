plugins {
    alias(libs.plugins.fireframe.android.feature)
    alias(libs.plugins.fireframe.android.library.compose)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.tatsuki.fireframe.feature.home"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":core:data"))

    testImplementation(project(":core:testing"))
    testImplementation(libs.junit)
    testImplementation(libs.androidx.espresso.core)
    testImplementation(libs.roborazzi)

    androidTestImplementation(project(":core:testing"))
}