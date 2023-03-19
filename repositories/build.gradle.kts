plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.volokhinaleksey.repositories"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(mapOf("path" to Modules.datasource)))
    implementation(project(mapOf("path" to Modules.models)))
    implementation(project(mapOf("path" to Modules.mapperUtils)))
    implementation(Retrofit.retrofit)
    implementation(Retrofit.converterGson)
    implementation(Retrofit.loggingOkHttp)
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