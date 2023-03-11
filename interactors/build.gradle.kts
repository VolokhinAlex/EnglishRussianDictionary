plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.volokhinaleksey.interactors"
    compileSdk = 33

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(mapOf("path" to ":models")))
    implementation(project(mapOf("path" to ":utils:mapperUtils")))
    implementation(project(mapOf("path" to ":repositories")))
    implementation(Android.androidCore)
    testImplementation(Tests.junit)
    androidTestImplementation(Tests.extJunit)
    androidTestImplementation(Tests.espressoCore)
}