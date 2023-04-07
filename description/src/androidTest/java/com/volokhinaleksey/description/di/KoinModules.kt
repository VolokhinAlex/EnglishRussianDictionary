package com.volokhinaleksey.description.di

import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.datasource.description.DescriptionDataSource
import com.volokhinaleksey.datasource.description.LocalDescriptionDataSource
import com.volokhinaleksey.datasource.description.LocalDescriptionDataSourceImpl
import com.volokhinaleksey.datasource.description.RemoteDescriptionDataSource
import com.volokhinaleksey.description.ui.WordDescriptionFragment
import com.volokhinaleksey.description.viewmodel.WordDescriptionViewModel
import com.volokhinaleksey.interactors.description.WordDescriptionInteractor
import com.volokhinaleksey.interactors.description.WordDescriptionInteractorImpl
import com.volokhinaleksey.network.networkHolder.ApiHolder
import com.volokhinaleksey.network.networkHolder.DictionaryApiHolder
import com.volokhinaleksey.network.networkService.ApiService
import com.volokhinaleksey.repositories.meanings.MeaningsRepository
import com.volokhinaleksey.repositories.meanings.MeaningsRepositoryImpl
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

        scoped { Dispatchers.IO }

        /**
         * Providing a dependency for WordDescriptionViewModel
         */

        viewModel { WordDescriptionViewModel(get(), get()) }
    }
}