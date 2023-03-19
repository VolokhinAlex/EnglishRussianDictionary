import org.gradle.api.JavaVersion

object Versions {

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
    const val koinTest = "3.3.3"

    // Coroutines
    const val coroutinesCore = "1.6.4"
    const val coroutinesAndroid = "1.6.4"
    const val coroutinesTest = "1.6.4"

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
    const val googleTruth = "1.1.3"
    const val mockito = "5.2.0"
    const val archCore = "2.2.0"
    const val fragmentTesting = "1.5.5"
    const val espressoContrib = "3.5.1"
    const val mockitoInline = "5.2.0"
    const val kotlinMockito = "4.1.0"
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
    const val models = ":models"
    const val database = ":database"
    const val datasource = ":datasource"
    const val networkUtils = ":utils:networkUtils"
    const val mapperUtils = ":utils:mapperUtils"
    const val interactors = ":interactors"
    const val network = ":network"
    const val sharedPreferencesHelper = ":utils:sharedPreferencesHelper"
    const val viewByIdUtils = ":utils:viewByIdUtils"

    const val searchScreen = ":search"
    const val descriptionScreen = ":description"
    const val favoriteScreen = ":favorite"
    const val historyScreen = ":history"
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
    const val koinTest = "io.insert-koin:koin-test:${Versions.koinTest}"
}

object Coroutines {
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
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
    const val googleTruth = "com.google.truth:truth:${Versions.googleTruth}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.kotlinMockito}"
    const val archCore = "androidx.arch.core:core-testing:${Versions.archCore}"
    const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragmentTesting}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espressoContrib}"
}