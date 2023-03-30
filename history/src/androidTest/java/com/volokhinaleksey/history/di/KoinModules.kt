package com.volokhinaleksey.history.di

import android.widget.ImageView
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.volokhinaleksey.core.ui.imageloader.CoilImageLoader
import com.volokhinaleksey.core.ui.imageloader.ImageLoader
import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.datasource.history.HistoryDataSource
import com.volokhinaleksey.datasource.history.LocalHistoryDataSource
import com.volokhinaleksey.history.ui.HistorySearchFragment
import com.volokhinaleksey.history.viewmodel.HistoryViewModel
import com.volokhinaleksey.interactors.history.HistoryInteractor
import com.volokhinaleksey.interactors.history.HistoryInteractorImpl
import com.volokhinaleksey.models.states.WordsState
import com.volokhinaleksey.network.networkHolder.ApiHolder
import com.volokhinaleksey.network.networkHolder.DictionaryApiHolder
import com.volokhinaleksey.network.networkService.ApiService
import com.volokhinaleksey.repositories.history.HistoryRepository
import com.volokhinaleksey.repositories.history.HistoryRepositoryImpl
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

        scoped { Dispatchers.IO }

        /**
         * Providing a dependency for CoilImageLoader
         */

        scoped<ImageLoader<ImageView>> { CoilImageLoader() }

        /**
         * Providing a dependency for HistoryViewModel
         */

        viewModel { HistoryViewModel(get(), get()) }
    }
}