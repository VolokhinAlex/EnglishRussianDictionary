plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.volokhinaleksey.search"

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
    implementation(project(mapOf("path" to Modules.networkUtils)))
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
    testImplementation(Tests.extJunit)
    testImplementation(Tests.mockito)
    testImplementation(Tests.archCore)
    testImplementation(Tests.mockitoKotlin)
    testImplementation(Tests.mockitoInline)
    testImplementation(Tests.googleTruth)
    testImplementation(Koin.koinTest)
    testImplementation(Coroutines.coroutinesTest)
    androidTestImplementation(Tests.espressoContrib)
    androidTestImplementation(Tests.mockito)
    androidTestImplementation(Tests.archCore)
    androidTestImplementation(Tests.googleTruth)
    androidTestImplementation(Koin.koinTest)
    androidTestImplementation(Tests.extJunit)
    androidTestImplementation(Tests.espressoCore)
    androidTestImplementation(Tests.kaspresso)
    androidTestImplementation(project(mapOf("path" to Modules.repositories)))
    androidTestImplementation(project(mapOf("path" to Modules.datasource)))
    androidTestImplementation(project(mapOf("path" to Modules.network)))
    androidTestImplementation(project(mapOf("path" to Modules.database)))
    androidTestImplementation(Room.roomRuntime)
    androidTestImplementation(Retrofit.retrofit)
    androidTestImplementation(Retrofit.converterGson)
    debugImplementation(Tests.fragmentTesting)
}