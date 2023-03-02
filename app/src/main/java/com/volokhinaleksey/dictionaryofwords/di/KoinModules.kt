package com.volokhinaleksey.dictionaryofwords.di

import android.widget.ImageView
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.volokhinaleksey.dictionaryofwords.datasource.description.DescriptionDataSource
import com.volokhinaleksey.dictionaryofwords.datasource.description.LocalDescriptionDataSource
import com.volokhinaleksey.dictionaryofwords.datasource.description.LocalDescriptionDataSourceImpl
import com.volokhinaleksey.dictionaryofwords.datasource.description.RemoteDescriptionDataSource
import com.volokhinaleksey.dictionaryofwords.datasource.history.HistoryDataSource
import com.volokhinaleksey.dictionaryofwords.datasource.history.LocalHistoryDataSource
import com.volokhinaleksey.dictionaryofwords.datasource.search.LocalSearchDataSource
import com.volokhinaleksey.dictionaryofwords.datasource.search.LocalSearchDataSourceImpl
import com.volokhinaleksey.dictionaryofwords.datasource.search.RemoteSearchDataSource
import com.volokhinaleksey.dictionaryofwords.datasource.search.SearchDataSource
import com.volokhinaleksey.dictionaryofwords.interactor.description.WordDescriptionInteractor
import com.volokhinaleksey.dictionaryofwords.interactor.description.WordDescriptionInteractorImpl
import com.volokhinaleksey.dictionaryofwords.interactor.history.HistoryInteractor
import com.volokhinaleksey.dictionaryofwords.interactor.history.HistoryInteractorImpl
import com.volokhinaleksey.dictionaryofwords.interactor.search.SearchWordsInteractor
import com.volokhinaleksey.dictionaryofwords.interactor.search.SearchWordsInteractorImpl
import com.volokhinaleksey.dictionaryofwords.repository.ApiHolder
import com.volokhinaleksey.dictionaryofwords.repository.ApiService
import com.volokhinaleksey.dictionaryofwords.repository.DictionaryApiHolder
import com.volokhinaleksey.dictionaryofwords.repository.history.HistoryRepository
import com.volokhinaleksey.dictionaryofwords.repository.history.HistoryRepositoryImpl
import com.volokhinaleksey.dictionaryofwords.repository.meanings.MeaningsRepository
import com.volokhinaleksey.dictionaryofwords.repository.meanings.MeaningsRepositoryImpl
import com.volokhinaleksey.dictionaryofwords.repository.search.SearchWordsRepository
import com.volokhinaleksey.dictionaryofwords.repository.search.SearchWordsRepositoryImpl
import com.volokhinaleksey.dictionaryofwords.room.database.DictionaryDatabase
import com.volokhinaleksey.dictionaryofwords.states.MeaningsState
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import com.volokhinaleksey.dictionaryofwords.ui.imageloaders.CoilImageLoader
import com.volokhinaleksey.dictionaryofwords.ui.imageloaders.ImageLoader
import com.volokhinaleksey.dictionaryofwords.viewmodel.DictionaryOfWordsViewModel
import com.volokhinaleksey.dictionaryofwords.viewmodel.HistoryViewModel
import com.volokhinaleksey.dictionaryofwords.viewmodel.WordDescriptionViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val DICTIONARY_DATABASE_NAME = "dictionary.db"

val databaseModule = module {
    single {
        Room
            .databaseBuilder(get(), DictionaryDatabase::class.java, DICTIONARY_DATABASE_NAME)
            .build()
    }
}

/**
 * A module for implementing dependencies for repositories and their data sources.
 */

val repositoryModule = module {

    /**
     * Dependency injection for SearchWordsRepositoryImpl()
     */

    single<SearchWordsRepository> {
        SearchWordsRepositoryImpl(get(), get())
    }

    /**
     * Dependency injection for RemoteSearchDataSource()
     */

    single<SearchDataSource> {
        RemoteSearchDataSource(get())
    }

    /**
     * Dependency injection for LocalDictionaryDataSource()
     */

    single<LocalSearchDataSource> {
        LocalSearchDataSourceImpl(get())
    }

    single<LocalDescriptionDataSource> {
        LocalDescriptionDataSourceImpl(get())
    }

    single<DescriptionDataSource> {
        RemoteDescriptionDataSource(get())
    }

    /**
     * Dependency injection for MeaningsRepository()
     */

    single<MeaningsRepository> {
        MeaningsRepositoryImpl(get(), get())
    }

    single<HistoryDataSource> {
        LocalHistoryDataSource(get())
    }

    single<HistoryRepository> {
        HistoryRepositoryImpl(get())
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


val historyScreen = module {
    factory<HistoryInteractor<WordsState>> {
        HistoryInteractorImpl(get())
    }
    viewModel { HistoryViewModel(get()) }
}