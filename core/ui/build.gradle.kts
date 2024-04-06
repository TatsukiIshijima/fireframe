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
    api(project(":core:designsystem"))
    api(project(":core:model"))

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
}