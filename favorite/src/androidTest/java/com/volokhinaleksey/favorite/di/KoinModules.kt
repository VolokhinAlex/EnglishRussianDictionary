package com.volokhinaleksey.favorite.di

import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.datasource.favorite.FavoriteDataSource
import com.volokhinaleksey.datasource.favorite.LocalFavoriteDataSource
import com.volokhinaleksey.favorite.ui.FavoriteWordsFragment
import com.volokhinaleksey.favorite.viewmodel.FavoriteViewModel
import com.volokhinaleksey.interactors.favorite.FavoriteInteractor
import com.volokhinaleksey.interactors.favorite.FavoriteInteractorImpl
import com.volokhinaleksey.network.networkHolder.ApiHolder
import com.volokhinaleksey.network.networkHolder.DictionaryApiHolder
import com.volokhinaleksey.network.networkService.ApiService
import com.volokhinaleksey.repositories.favorite.FavoriteRepository
import com.volokhinaleksey.repositories.favorite.FavoriteRepositoryImpl
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

        scoped { Dispatchers.IO }

        /**
         * Providing a dependency for FavoriteViewModel
         */

        viewModel { FavoriteViewModel(get(), get()) }
    }
}