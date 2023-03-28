package com.volokhinaleksey.search.di

import android.widget.ImageView
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.volokhinaleksey.core.ui.imageloader.CoilImageLoader
import com.volokhinaleksey.core.ui.imageloader.ImageLoader
import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.datasource.search.LocalSearchDataSource
import com.volokhinaleksey.datasource.search.LocalSearchDataSourceImpl
import com.volokhinaleksey.datasource.search.RemoteSearchDataSource
import com.volokhinaleksey.datasource.search.SearchDataSource
import com.volokhinaleksey.interactors.search.SearchWordsInteractor
import com.volokhinaleksey.interactors.search.SearchWordsInteractorImpl
import com.volokhinaleksey.models.states.WordsState
import com.volokhinaleksey.network.networkHolder.ApiHolder
import com.volokhinaleksey.network.networkHolder.DictionaryApiHolder
import com.volokhinaleksey.network.networkService.ApiService
import com.volokhinaleksey.repositories.search.SearchWordsRepository
import com.volokhinaleksey.repositories.search.SearchWordsRepositoryImpl
import com.volokhinaleksey.search.ui.DictionaryOfWordsFragment
import com.volokhinaleksey.search.viewmodel.DictionaryOfWordsViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val DICTIONARY_DATABASE_NAME = "dictionary.db"

/**
 * A module for providing a dependency for a database
 */

val databaseModuleTesting = module {
    single {
        Room
            .databaseBuilder(get(), DictionaryDatabase::class.java, DICTIONARY_DATABASE_NAME)
            .build()
    }
}

/**
 * A module for implementing dependencies for repositories and their data sources.
 */

val repositoryModuleTesting = module {

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
}

/**
 * Module for implementing dependencies for working with the network
 */

val networkModuleTesting = module {

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
     * Providing a dependency to work with retrofit
     */

    single {
        Retrofit.Builder()
            .baseUrl("https://dictionary.skyeng.ru/api/public/v1/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
            .create(ApiService::class.java)
    }
}

/**
 * Module for embedding dependencies in the word search screen
 */

val dictionaryOfWordsScreenTesting = module {

    /**
     * The Scope that lives while the DictionaryOfWordsFragment screen lives
     */

    /**
     * The Scope that lives while the DictionaryOfWordsFragment screen lives
     */

    scope<DictionaryOfWordsFragment> {

        /**
         * Providing a dependency for SearchWordsInteractorImpl
         */

        /**
         * Providing a dependency for SearchWordsInteractorImpl
         */

        scoped<SearchWordsInteractor<WordsState>> {
            SearchWordsInteractorImpl(get())
        }

        /**
         * Providing a dependency for DictionaryOfWordsViewModel
         */

        scoped { Dispatchers.IO }

        /**
         * Providing a dependency for DictionaryOfWordsViewModel
         */

        viewModel { DictionaryOfWordsViewModel(get(), get()) }

        /**
         * Providing a dependency for ImageLoader
         */

        /**
         * Providing a dependency for ImageLoader
         */

        scoped<ImageLoader<ImageView>> { CoilImageLoader() }
    }
}