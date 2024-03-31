plugins {
    alias(libs.plugins.fireframe.android.feature)
    alias(libs.plugins.fireframe.android.library.compose)
}

android {
    namespace = "com.tatsuki.fireframe.feature.slideshow"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    testImplementation(project(":core:testing"))
    testImplementation(libs.junit)
    testImplementation(libs.androidx.espresso.core)
    testImplementation(libs.roborazzi)
}