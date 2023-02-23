package com.volokhinaleksey.dictionaryofwords.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.volokhinaleksey.dictionaryofwords.repository.ApiHolder
import com.volokhinaleksey.dictionaryofwords.repository.ApiService
import com.volokhinaleksey.dictionaryofwords.repository.DictionaryApiHolder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
interface NetworkModule {

    @Binds
    @Singleton
    fun apiHolder(apiHolder: DictionaryApiHolder): ApiHolder

    companion object {

        private const val BASE_URL_NAME: String = "BASE_URL"

        @Provides
        @Singleton
        @Named(BASE_URL_NAME)
        fun baseUrl(): String = "https://dictionary.skyeng.ru/api/public/v1/"

        @Provides
        @Singleton
        fun gson(): Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        @Provides
        @Singleton
        fun okHttpLogger(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        @Provides
        @Singleton
        fun apiService(
            @Named(BASE_URL_NAME) baseUrl: String,
            gson: Gson,
            client: OkHttpClient
        ): ApiService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(ApiService::class.java)

    }
}