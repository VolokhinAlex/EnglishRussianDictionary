import org.gradle.api.JavaVersion

object Versions {
    // RxJava
    const val rxJavaAndroid = "3.0.2"
    const val rxJavaCore = "3.1.5"

    // Room
    const val roomKtx = "2.5.0"
    const val roomCompiler = "2.5.0"
    const val roomRuntime = "2.5.0"

    // Navigation Component
    const val navigation = "2.5.3"

    // Koin
    const val koinCore = "3.3.3"
    const val androidKoin = "3.3.3"
    const val compatAndroidKoin = "3.3.3"

    // Coroutines
    const val coroutinesCore = "1.6.4"
    const val coroutinesAndroid = "1.6.4"

    // Lifecycle
    const val lifecycleLivedata = "2.5.1"
    const val lifecycleViewModel = "2.5.1"

    // Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val loggingOkHttp = "4.9.3"

    // Coil
    const val coil = "2.2.2"

    // Android Ktx
    const val ktx = "1.7.0"

    // Design
    const val appcompat = "1.6.1"
    const val material = "1.8.0"
    const val constraintLayout = "1.8.0"

    // Tests
    const val junit = "4.13.2"
    const val extJunit = "1.1.5"
    const val espressoCore = "3.5.1"
}

object Config {
    const val applicationId = "com.volokhinaleksey.dictionaryofwords"
    const val minSdk = 27
    const val targetSdk = 33
    const val compileSdk = 33
    val javaVersion = JavaVersion.VERSION_1_8
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Modules {
    const val app = ":app"
    const val repositories = ":repositories"
    const val core = ":core"
    const val networkUtils = ":networkUtils"

    const val searchScreen = ":searchScreen"
    const val descriptionScreen = ":descriptionScreen"
    const val favoriteScreen = ":favoriteScreen"
    const val historyScreen = ":historyScreen"
}

object RxJava {
    const val rxJavaAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxJavaAndroid}"
    const val rxJavaCore = "io.reactivex.rxjava3:rxjava:${Versions.rxJavaCore}"
}

object Room {
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomRuntime}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object Navigation {
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}

object Koin {
    const val koinCore = "io.insert-koin:koin-core:${Versions.koinCore}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.androidKoin}"
    const val koinAndroidCompat = "io.insert-koin:koin-android-compat:${Versions.compatAndroidKoin}"
}

object Coroutines {
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object Lifecycle {
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleLivedata}"
    const val viewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModel}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val loggingOkHttp = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingOkHttp}"
}

object Coil {
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Android {
    const val androidCore = "androidx.core:core-ktx:${Versions.ktx}"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constrainLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object Tests {
    const val junit = "junit:junit:${Versions.junit}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}