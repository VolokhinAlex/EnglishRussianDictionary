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
    implementation(project(mapOf("path" to Modules.models)))
    implementation(project(mapOf("path" to Modules.mapperUtils)))
    implementation(project(mapOf("path" to Modules.repositories)))
    implementation(Android.androidCore)
    testImplementation(Tests.junit)
    testImplementation(Tests.googleTruth)
    testImplementation(Tests.archCore)
    testImplementation(Tests.mockito)
    testImplementation(Tests.mockitoKotlin)
    testImplementation(Tests.mockitoInline)
    testImplementation(Coroutines.coroutinesTest)
    androidTestImplementation(Tests.extJunit)
    androidTestImplementation(Tests.espressoCore)
}