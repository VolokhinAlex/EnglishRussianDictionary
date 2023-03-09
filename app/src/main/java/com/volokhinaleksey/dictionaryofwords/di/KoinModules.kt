package com.volokhinaleksey.dictionaryofwords.di

import android.widget.ImageView
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.volokhinaleksey.core.ui.imageloader.CoilImageLoader
import com.volokhinaleksey.core.ui.imageloader.ImageLoader
import com.volokhinaleksey.database.database.DictionaryDatabase
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
import com.volokhinaleksey.description.ui.WordDescriptionFragment
import com.volokhinaleksey.description.viewmodel.WordDescriptionViewModel
import com.volokhinaleksey.favorite.ui.FavoriteWordsFragment
import com.volokhinaleksey.favorite.viewmodel.FavoriteViewModel
import com.volokhinaleksey.history.ui.HistorySearchFragment
import com.volokhinaleksey.history.viewmodel.HistoryViewModel
import com.volokhinaleksey.interactors.description.WordDescriptionInteractor
import com.volokhinaleksey.interactors.description.WordDescriptionInteractorImpl
import com.volokhinaleksey.interactors.favorite.FavoriteInteractor
import com.volokhinaleksey.interactors.favorite.FavoriteInteractorImpl
import com.volokhinaleksey.interactors.history.HistoryInteractor
import com.volokhinaleksey.interactors.history.HistoryInteractorImpl
import com.volokhinaleksey.interactors.search.SearchWordsInteractor
import com.volokhinaleksey.interactors.search.SearchWordsInteractorImpl
import com.volokhinaleksey.models.states.WordsState
import com.volokhinaleksey.network.networkHolder.ApiHolder
import com.volokhinaleksey.network.networkHolder.DictionaryApiHolder
import com.volokhinaleksey.network.networkService.ApiService
import com.volokhinaleksey.repositories.favorite.FavoriteRepository
import com.volokhinaleksey.repositories.favorite.FavoriteRepositoryImpl
import com.volokhinaleksey.repositories.history.HistoryRepository
import com.volokhinaleksey.repositories.history.HistoryRepositoryImpl
import com.volokhinaleksey.repositories.meanings.MeaningsRepository
import com.volokhinaleksey.repositories.meanings.MeaningsRepositoryImpl
import com.volokhinaleksey.repositories.search.SearchWordsRepository
import com.volokhinaleksey.repositories.search.SearchWordsRepositoryImpl
import com.volokhinaleksey.search.ui.DictionaryOfWordsFragment
import com.volokhinaleksey.search.viewmodel.DictionaryOfWordsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val DICTIONARY_DATABASE_NAME = "dictionary.db"

/**
 * A module for providing a dependency for a database
 */

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
     * Providing a dependency for SearchWordsRepositoryImpl()
     */

    single<SearchWordsRepository> {
        SearchWordsRepositoryImpl(get(), get())
    }

    /**
     * Providing a dependency for RemoteSearchDataSource()
     */

    single<SearchDataSource> {
        RemoteSearchDataSource(get())
    }

    /**
     * Providing a dependency for LocalSearchDataSourceImpl()
     */

    single<LocalSearchDataSource> {
        LocalSearchDataSourceImpl(get())
    }

    /**
     * Providing a dependency for LocalDescriptionDataSourceImpl()
     */

    single<LocalDescriptionDataSource> {
        LocalDescriptionDataSourceImpl(get())
    }

    /**
     * Providing a dependency for RemoteDescriptionDataSource()
     */

    single<DescriptionDataSource> {
        RemoteDescriptionDataSource(get())
    }

    /**
     * Providing a dependency for MeaningsRepository()
     */

    single<MeaningsRepository> {
        MeaningsRepositoryImpl(get(), get())
    }

    /**
     * Providing a dependency for LocalHistoryDataSource()
     */

    single<HistoryDataSource> {
        LocalHistoryDataSource(get())
    }

    /**
     * Providing a dependency for HistoryRepositoryImpl()
     */

    single<HistoryRepository> {
        HistoryRepositoryImpl(get())
    }

    /**
     * Providing a dependency for LocalFavoriteDataSource()
     */

    single<FavoriteDataSource> {
        LocalFavoriteDataSource(get())
    }

    /**
     * Providing a dependency for FavoriteRepositoryImpl()
     */

    single<FavoriteRepository> {
        FavoriteRepositoryImpl(get())
    }
}

/**
 * Module for implementing dependencies for working with the network
 */

val networkModule = module {

    /**
     * Providing a dependency for ApiHolder
     */

    single<ApiHolder> { DictionaryApiHolder(get()) }

    /**
     * Providing a dependency for the gson converter
     */

    single<Gson> {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    /**
     * Providing a dependency for OkHttpClient with HttpLoggingInterceptor
     */

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    /**
     * Providing a dependency to work with retrofit
     */

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

    /**
     * The Scope that lives while the DictionaryOfWordsFragment screen lives
     */

    scope<DictionaryOfWordsFragment> {

        /**
         * Providing a dependency for SearchWordsInteractorImpl
         */

        scoped<SearchWordsInteractor<WordsState>> {
            SearchWordsInteractorImpl(
                get()
            )
        }

        /**
         * Providing a dependency for DictionaryOfWordsViewModel
         */

        viewModel { DictionaryOfWordsViewModel(get()) }

        /**
         * Providing a dependency for ImageLoader
         */

        scoped<ImageLoader<ImageView>> { CoilImageLoader() }
    }
}

/**
 * Module for embedding dependencies in the word details screen
 */

val wordDescriptionScreen = module {

    /**
     * The Scope that lives while the WordDescriptionFragment screen lives
     */

    scope<WordDescriptionFragment> {

        /**
         * Providing a dependency for WordDescriptionInteractorImpl
         */

        scoped<WordDescriptionInteractor> {
            WordDescriptionInteractorImpl(get())
        }

        /**
         * Providing a dependency for WordDescriptionViewModel
         */

        viewModel { WordDescriptionViewModel(get()) }
    }
}


val historyScreen = module {

    /**
     * The Scope that lives while the HistorySearchFragment screen lives
     */

    scope<HistorySearchFragment> {

        /**
         * Providing a dependency for HistoryInteractorImpl
         */

        scoped<HistoryInteractor<WordsState>> {
            HistoryInteractorImpl(get())
        }

        /**
         * Providing a dependency for CoilImageLoader
         */

        scoped<ImageLoader<ImageView>> { CoilImageLoader() }

        /**
         * Providing a dependency for HistoryViewModel
         */

        viewModel { HistoryViewModel(get()) }
    }
}

val favoriteScreen = module {

    /**
     * The Scope that lives while the FavoriteWordsFragment screen lives
     */

    scope<FavoriteWordsFragment> {

        /**
         * Providing a dependency for FavoriteInteractorImpl
         */

        scoped<FavoriteInteractor> {
            FavoriteInteractorImpl(get())
        }

        /**
         * Providing a dependency for FavoriteViewModel
         */

        viewModel { FavoriteViewModel(get()) }
    }
}