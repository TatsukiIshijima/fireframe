plugins {
    alias(libs.plugins.fireframe.android.library)
    alias(libs.plugins.fireframe.android.hilt)
}

android {
    namespace = "com.tatsuki.fireframe.core.data"
}

dependencies {
    api(project(":core:datastore"))
    api(project(":core:network"))
}