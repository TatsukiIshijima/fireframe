plugins {
    alias(libs.plugins.fireframe.android.feature)
    alias(libs.plugins.fireframe.android.library.compose)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.tatsuki.fireframe.feature.mediaselector"

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
    implementation(project(":core:data"))
    implementation(libs.google.accompanist.permissions)

    testImplementation(project(":core:testing"))
    testImplementation(libs.junit)
    testImplementation(libs.androidx.espresso.core)
    testImplementation(libs.roborazzi)

    androidTestImplementation(project(":core:testing"))
}