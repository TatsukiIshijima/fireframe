import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.fireframe.android.library)
    alias(libs.plugins.fireframe.android.hilt)
    id("kotlinx-serialization")
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "com.tatsuki.fireframe.core.network"

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    defaultConfig {
        // If local build, create local.properties in the root of the project.
        // And write openWeatherApiKey value in local.properties file.
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            val localProperties = Properties()
            localProperties.load(FileInputStream(localPropertiesFile))
            buildConfigField("String", "OPEN_WEATHER_API_KEY", "\"${localProperties["openWeatherApiKey"]}\"")
        }

        buildConfigField("String", "OPEN_WEATHER_API_BASE_URL", "\"https://api.openweathermap.org/data/3.0/\"")
    }
}

dependencies {
    api(project(":core:common"))
    api(project(":core:model"))

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)

    implementation(libs.hilt.android.testing)

    testImplementation(project(":core:testing"))
}