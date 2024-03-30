plugins {
    alias(libs.plugins.fireframe.android.library)
    alias(libs.plugins.fireframe.android.library.compose)
}

android {
    namespace = "com.tatsuki.fireframe.core.ui"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    api(libs.androidx.material3)
}