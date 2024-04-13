plugins {
    alias(libs.plugins.fireframe.android.library)
    alias(libs.plugins.fireframe.android.hilt)
}

android {
    namespace = "com.tatsuki.fireframe.core.datastore"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    api(libs.androidx.dataStore.preferences)
    api(project(":core:model"))

    testImplementation(libs.hilt.android.testing)
    testImplementation(project(":core:testing"))
}