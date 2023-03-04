package com.volokhinaleksey.dictionaryofwords.di

import android.widget.ImageView
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.datasource.ApiHolder
import com.volokhinaleksey.datasource.ApiService
import com.volokhinaleksey.datasource.DictionaryApiHolder
import com.volokhinaleksey.datasource.description.DescriptionDataSource
import com.volokhinaleksey.datasource.description.LocalDescriptionDataSource
import com.volokhinaleksey.datasource.description.LocalDescriptionDataSourceImpl
import com.volokhinaleksey.datasource.description.RemoteDescriptionDataSource
import com.volokhinaleksey.datasource.favorite.FavoriteDataSource
import com.volokhinaleksey.datasource.favorite.LocalFavoriteDataSource
import com.volokhinaleksey.datasource.history.HistoryDataSource
import com.volokhinaleksey.datasource.history.LocalHistoryDataSource
import com.volokhinaleksey.datasource.search.LocalSearchDataSource
import com.volokhinaleksey.datasource.search.LocalSearchDataSourceImpl
import com.volokhinaleksey.datasource.search.RemoteSearchDataSource
import com.volokhinaleksey.datasource.search.SearchDataSource
import com.volokhinaleksey.models.states.WordsState
import com.volokhinaleksey.core.ui.imageloader.CoilImageLoader
import com.volokhinaleksey.core.ui.imageloader.ImageLoader
import com.volokhinaleksey.dictionaryofwords.viewmodel.DictionaryOfWordsViewModel
import com.volokhinaleksey.dictionaryofwords.viewmodel.FavoriteViewModel
import com.volokhinaleksey.dictionaryofwords.viewmodel.HistoryViewModel
import com.volokhinaleksey.dictionaryofwords.viewmodel.WordDescriptionViewModel
import com.volokhinaleksey.interactors.description.WordDescriptionInteractor
import com.volokhinaleksey.interactors.description.WordDescriptionInteractorImpl
import com.volokhinaleksey.interactors.favorite.FavoriteInteractor
import com.volokhinaleksey.interactors.favorite.FavoriteInteractorImpl
import com.volokhinaleksey.interactors.history.HistoryInteractor
import com.volokhinaleksey.interactors.history.HistoryInteractorImpl
import com.volokhinaleksey.interactors.search.SearchWordsInteractor
import com.volokhinaleksey.interactors.search.SearchWordsInteractorImpl
import com.volokhinaleksey.repositories.favorite.FavoriteRepository
import com.volokhinaleksey.repositories.favorite.FavoriteRepositoryImpl
import com.volokhinaleksey.repositories.history.HistoryRepository
import com.volokhinaleksey.repositories.history.HistoryRepositoryImpl
import com.volokhinaleksey.repositories.meanings.MeaningsRepository
import com.volokhinaleksey.repositories.meanings.MeaningsRepositoryImpl
import com.volokhinaleksey.repositories.search.SearchWordsRepository
import com.volokhinaleksey.repositories.search.SearchWordsRepositoryImpl
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

    /**
     * Favorite Screen
     */

    single<FavoriteDataSource> {
        LocalFavoriteDataSource(get())
    }

    single<FavoriteRepository> {
        FavoriteRepositoryImpl(get())
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
    factory<SearchWordsInteractor<WordsState>> {
        SearchWordsInteractorImpl(
            get()
        )
    }
    viewModel { DictionaryOfWordsViewModel(get()) }
    factory<ImageLoader<ImageView>> { CoilImageLoader() }
}

/**
 * Module for embedding dependencies in the word details screen
 */

val wordDescriptionScreen = module {
    factory<WordDescriptionInteractor> {
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

val favoriteScreen = module {
    factory<FavoriteInteractor> {
        FavoriteInteractorImpl(get())
    }
    viewModel { FavoriteViewModel(get()) }
}