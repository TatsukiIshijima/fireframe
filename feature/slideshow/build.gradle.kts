plugins {
    alias(libs.plugins.fireframe.android.feature)
    alias(libs.plugins.fireframe.android.library.compose)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.tatsuki.fireframe.feature.slideshow"

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

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