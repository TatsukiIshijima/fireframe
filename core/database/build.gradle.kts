plugins {
    alias(libs.plugins.fireframe.android.library)
    alias(libs.plugins.fireframe.android.hilt)
    alias(libs.plugins.fireframe.android.room)
}

android {
    namespace = "com.tatsuki.fireframe.core.database"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(project(":core:model"))
}