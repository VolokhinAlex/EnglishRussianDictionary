package com.volokhinaleksey.dictionaryofwords.repository

import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningsDataDTO
import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for working with the API using Retrofit
 */

interface ApiService {

    /**
     * A method for getting a list of words for a specific query.
     * @param wordToSearch - The query for which you need to find the words
     */

    @GET("words/search")
    suspend fun getWordsBySearch(@Query("search") wordToSearch: String): List<WordDTO>

    @GET("meanings")
    suspend fun getMeanings(@Query("ids") meaningId: Long): List<MeaningsDataDTO>
}