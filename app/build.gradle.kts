import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.github.triplet.gradle.androidpublisher.ReleaseStatus.IN_PROGRESS
import java.io.FileInputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Properties

plugins {
    alias(libs.plugins.fireframe.android.application)
    alias(libs.plugins.fireframe.android.application.compose)
    alias(libs.plugins.fireframe.android.application.firebase)
    alias(libs.plugins.fireframe.android.hilt)
    alias(libs.plugins.gradle.play.publisher)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.tatsuki.fireframe"

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    defaultConfig {
        applicationId = "com.tatsuki.fireframe"
        versionCode = 7
        versionName = "1.0.5"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            // If local build, create keystore.properties in the root of the project.
            // And the store file path, store password, keyAlias, and keyPassword.
            val keystorePropertiesFile = rootProject.file("keystore.properties")
            if (keystorePropertiesFile.exists()) {
                val keystoreProperties = Properties()
                keystoreProperties.load(FileInputStream(keystorePropertiesFile))
                storeFile = file(keystoreProperties["storeFile"] as String)
                storePassword = keystoreProperties["storePassword"] as String
                keyAlias = keystoreProperties["keyAlias"] as String
                keyPassword = keystoreProperties["keyPassword"] as String
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    applicationVariants.all {
        outputs.all {
            if (this is BaseVariantOutputImpl) {
                val currentDate = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("MMdd")
                val formattedDate = currentDate.format(formatter)

                outputFileName =
                    "fireframe-${name}-${versionName}(${versionCode})-${formattedDate}.${outputFile.extension}"
            }
            // FIXME: Apply aab file name.
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

play {
    track.set("production")
    userFraction.set(0.001)
    releaseName.set("${android.defaultConfig.versionName}(${android.defaultConfig.versionCode})")
    releaseStatus.set(IN_PROGRESS)
    serviceAccountCredentials.set(rootProject.file("fireframe-service-account-key.json"))
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:remoteconfig"))
    implementation(project(":core:ui"))
    implementation(project(":feature:home"))
    implementation(project(":feature:mediaselector"))
    implementation(project(":feature:setting"))
    implementation(project(":feature:slideshow"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.kt)

    ksp(libs.hilt.compiler)

    testImplementation(project(":core:testing"))
    testImplementation(libs.junit)
    testImplementation(libs.androidx.espresso.core)
    testImplementation(libs.roborazzi)

    kspTest(libs.hilt.compiler)

    androidTestImplementation(project(":core:testing"))
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.test.manifest)
}