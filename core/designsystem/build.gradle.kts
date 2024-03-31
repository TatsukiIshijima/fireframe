plugins {
    alias(libs.plugins.fireframe.android.library)
    alias(libs.plugins.fireframe.android.library.compose)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.tatsuki.fireframe.core.designsystem"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(libs.androidx.material3)

    testImplementation(libs.robolectric)
}