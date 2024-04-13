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
    api(project(":core:model"))
}