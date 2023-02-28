package com.volokhinaleksey.dictionaryofwords.di

import android.widget.ImageView
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.volokhinaleksey.dictionaryofwords.datasource.DictionaryDataSource
import com.volokhinaleksey.dictionaryofwords.datasource.LocalDictionaryDataSource
import com.volokhinaleksey.dictionaryofwords.datasource.RemoteDictionaryDataSource
import com.volokhinaleksey.dictionaryofwords.interactor.search.SearchWordsInteractor
import com.volokhinaleksey.dictionaryofwords.interactor.search.SearchWordsInteractorImpl
import com.volokhinaleksey.dictionaryofwords.interactor.description.WordDescriptionInteractor
import com.volokhinaleksey.dictionaryofwords.interactor.description.WordDescriptionInteractorImpl
import com.volokhinaleksey.dictionaryofwords.repository.*
import com.volokhinaleksey.dictionaryofwords.states.MeaningsState
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import com.volokhinaleksey.dictionaryofwords.ui.imageloaders.CoilImageLoader
import com.volokhinaleksey.dictionaryofwords.ui.imageloaders.ImageLoader
import com.volokhinaleksey.dictionaryofwords.viewmodel.DictionaryOfWordsViewModel
import com.volokhinaleksey.dictionaryofwords.viewmodel.WordDescriptionViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A module for implementing dependencies for repositories and their data sources.
 */

val repositoryModule = module {

    /**
     * Dependency injection for SearchWordsRepositoryImpl()
     */

    single<SearchWordsRepository> {
        SearchWordsRepositoryImpl(
            get(named(REMOTE_SOURCE)),
            get(named(LOCAL_SOURCE))
        )
    }

    /**
     * Dependency injection for RemoteDictionaryDataSource()
     */

    single<DictionaryDataSource>(named(REMOTE_SOURCE)) {
        RemoteDictionaryDataSource(get())
    }

    /**
     * Dependency injection for LocalDictionaryDataSource()
     */

    single<DictionaryDataSource>(named(LOCAL_SOURCE)) {
        LocalDictionaryDataSource()
    }

    /**
     * Dependency injection for MeaningsRepository()
     */

    single<MeaningsRepository> {
        MeaningsRepositoryImpl(
            get(named(REMOTE_SOURCE)),
            get(named(LOCAL_SOURCE))
        )
    }
}

/**
 * Module for implementing dependencies for working with the network
 */

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

/**
 * Module for embedding dependencies in the word search screen
 */

val dictionaryOfWordsScreen = module {
    factory<SearchWordsInteractor<WordsState>> { SearchWordsInteractorImpl(get()) }
    viewModel { DictionaryOfWordsViewModel(get()) }
    factory<ImageLoader<ImageView>> { CoilImageLoader() }
}

/**
 * Module for embedding dependencies in the word details screen
 */

val wordDescriptionScreen = module {
    factory<WordDescriptionInteractor<MeaningsState>> {
        WordDescriptionInteractorImpl(get())
    }
    viewModel { WordDescriptionViewModel(get()) }
}