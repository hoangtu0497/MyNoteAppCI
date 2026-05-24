import com.google.firebase.appdistribution.gradle.firebaseAppDistribution
import com.android.build.api.dsl.ApplicationBuildType
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.firebase.appdistribution)
}

android {
    namespace = "com.torilab.assignment.mynoteapp"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.torilab.assignment.mynoteapp"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 2
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    val properties = Properties()
    val localPropertiesFile = project.rootProject.file("local.properties")
    val isLocalPropertiesFileExist = localPropertiesFile.exists()
    if (isLocalPropertiesFileExist) {
        properties.load(localPropertiesFile.reader())
    }

    signingConfigs {
        // getByName("debug") {
        //     storeFile = file("${System.getProperty("user.home")}/.android/debug.keystore")
        //     storePassword = "android"
        //     keyAlias = "androiddebugkey"
        //     keyPassword = "android"
        // }
        // a
        // b

        create("release") {
            keyAlias = System.getenv("KEY_ALIAS").takeUnless { it.isNullOrBlank() }
                ?: properties.getProperty("UPLOAD_KEY_ALIAS")
            keyPassword = System.getenv("KEY_PASSWORD").takeUnless { it.isNullOrBlank() }
                ?: properties.getProperty("UPLOAD_KEY_PASSWORD")
            storePassword = System.getenv("STORE_PASSWORD").takeUnless { it.isNullOrBlank() }
                ?: properties.getProperty("UPLOAD_STORE_PASSWORD")
            storeFile = file(System.getenv("UPLOAD_STORE_FILE").takeUnless { it.isNullOrBlank() }
                ?: "release.jks"
                ?: properties.getProperty("UPLOAD_STORE_FILE"))
                // ?: "./keystores/upload-keystore.keystore")
        }
    }

    buildTypes {
        release {
            releaseConfig()
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            // firebaseCrashlytics {
            //     mappingFileUploadEnabled = true
            // }

            firebaseAppDistribution {
                appId = "1:555077899384:android:9ac8acd6455bf6f595498d"
                artifactType = "APK"
                // releaseNotesFile = "commit_history.txt"
                releaseNotes = "Automated build from GitHub Actions."
                groups = "tate"
                serviceCredentialsFile = "$rootDir/firebase-service-account.json"
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":designsystem"))
    implementation(project(":note-component"))
    implementation(project(":notes-ui"))
    implementation(project(":addnote-ui"))
    implementation(project(":notedetail-ui"))

    implementation(libs.compose.material3)
    implementation(libs.compose.navigation)
}

fun ApplicationBuildType.debugConfig() {
    isShrinkResources = false
    isMinifyEnabled = false
    isDebuggable = true
}

fun ApplicationBuildType.releaseConfig() {
    isShrinkResources = true
    isMinifyEnabled = true
    isDebuggable = false
}
