plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.volokhinaleksey.favorite"

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
    implementation(project(mapOf("path" to Modules.core)))
    implementation(project(mapOf("path" to Modules.models)))
    implementation(project(mapOf("path" to Modules.interactors)))
    implementation(Koin.koinCore)
    implementation(Koin.koinAndroid)
    implementation(Koin.koinAndroidCompat)
    implementation(Navigation.navigationUi)
    implementation(Navigation.navigationFragment)
    implementation(Coil.coil)
    implementation(Lifecycle.livedata)
    implementation(Lifecycle.viewModel)
    implementation(Android.androidCore)
    implementation(Design.appcompat)
    implementation(Design.material)
    testImplementation(Tests.junit)
    testImplementation(Koin.koinTest)
    testImplementation(Tests.mockito)
    testImplementation(Coroutines.coroutinesTest)
    testImplementation(Tests.googleTruth)
    testImplementation(Tests.archCore)
    androidTestImplementation(Tests.extJunit)
    androidTestImplementation(Tests.espressoCore)
}