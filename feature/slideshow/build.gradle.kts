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

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}