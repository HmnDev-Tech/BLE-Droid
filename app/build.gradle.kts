plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.bledroid"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.bledroid"
        minSdk = 26
        targetSdk = 36
        versionCode = 4
        versionName = "1.6.1"
    }

    val sharedSigning = System.getenv("KEYSTORE_PATH") != null

    signingConfigs {
        create("shared") {
            if (sharedSigning) {
                storeFile = file(System.getenv("KEYSTORE_PATH")!!)
                storePassword = System.getenv("KEYSTORE_PASSWORD")
                keyAlias = System.getenv("KEY_ALIAS")
                keyPassword = System.getenv("KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = if (sharedSigning) signingConfigs.getByName("shared") else null
        }
        debug {
            applicationIdSuffix = ".debug"
            if (sharedSigning) {
                signingConfig = signingConfigs.getByName("shared")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Compose
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.graphics.shapes)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material.icons)
    debugImplementation(libs.compose.ui.tooling)

    // AndroidX
    implementation(libs.activity.compose)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.navigation.compose)

    // Coroutines
    implementation(libs.kotlinx.coroutines)
}
