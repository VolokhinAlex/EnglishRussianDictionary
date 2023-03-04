plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.volokhinaleksey.core"
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
    implementation(project(mapOf("path" to ":utils:networkUtils")))
    implementation(RxJava.rxJavaCore)
    implementation(RxJava.rxJavaAndroid)
    implementation(Coil.coil)
    implementation(Lifecycle.livedata)
    implementation(Lifecycle.viewModel)
    implementation(Android.androidCore)
    implementation(Design.appcompat)
    implementation(Design.material)
    testImplementation(Tests.junit)
    androidTestImplementation(Tests.extJunit)
    androidTestImplementation(Tests.espressoCore)
}