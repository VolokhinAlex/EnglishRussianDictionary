package com.volokhinaleksey.dictionaryofwords.repository

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Interface for working with the remote API.
 */

interface ApiHolder {
    val apiService: ApiService
}

/**
 * Implementation of an interface for working with a remote API using Retrofit.
 */

object DictionaryApiHolder : ApiHolder {

    private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"

    override val apiService: ApiService by lazy {
        val gson: Gson =
            GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(BaseInterceptor())
        okHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient.build())
            .build()
            .create(ApiService::class.java)
    }

}

class BaseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}