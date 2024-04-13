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
    api(libs.androidx.core.ktx)

    implementation(libs.androidx.ui.graphics)
    implementation(libs.coil.kt.compose)

    testImplementation(libs.robolectric)
}