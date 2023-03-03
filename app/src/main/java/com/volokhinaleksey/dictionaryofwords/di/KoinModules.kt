package com.volokhinaleksey.dictionaryofwords.di

import android.widget.ImageView
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.volokhinaleksey.dictionaryofwords.datasource.DictionaryDataSource
import com.volokhinaleksey.dictionaryofwords.datasource.LocalDictionaryDataSource
import com.volokhinaleksey.dictionaryofwords.datasource.RemoteDictionaryDataSource
import com.volokhinaleksey.dictionaryofwords.interactor.DictionaryOfWordsInteractor
import com.volokhinaleksey.dictionaryofwords.interactor.Interactor
import com.volokhinaleksey.dictionaryofwords.model.WordData
import com.volokhinaleksey.dictionaryofwords.repository.*
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import com.volokhinaleksey.dictionaryofwords.ui.imageloaders.CoilImageLoader
import com.volokhinaleksey.dictionaryofwords.ui.imageloaders.ImageLoader
import com.volokhinaleksey.dictionaryofwords.viewmodel.DictionaryOfWordsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {
    single<Repository<List<WordData>>> {
        DictionaryOfWordsRepository(
            get(named(REMOTE_SOURCE)),
            get(named(LOCAL_SOURCE))
        )
    }

    single<DictionaryDataSource>(named(REMOTE_SOURCE)) {
        RemoteDictionaryDataSource(get())
    }
    single<DictionaryDataSource>(named(LOCAL_SOURCE)) {
        LocalDictionaryDataSource()
    }
}

val networkModule = module {
    single<ApiHolder> { DictionaryApiHolder(get()) }

    single<Gson> {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    single<ApiService> {
        Retrofit.Builder()
            .baseUrl("https://dictionary.skyeng.ru/api/public/v1/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
            .create(ApiService::class.java)
    }
}

val dictionaryOfWordsScreen = module {
    factory<Interactor<WordsState>> { DictionaryOfWordsInteractor(get()) }
    viewModel { DictionaryOfWordsViewModel(get()) }
    factory<ImageLoader<ImageView>> { CoilImageLoader() }
}