plugins {
    alias(libs.plugins.fireframe.android.library)
    alias(libs.plugins.fireframe.android.hilt)
}

android {
    namespace = "com.tatsuki.fireframe.core.remoteconfig"
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.remote.config)
}