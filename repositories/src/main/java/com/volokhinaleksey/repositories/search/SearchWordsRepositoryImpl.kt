package com.volokhinaleksey.repositories.search

import com.volokhinaleksey.datasource.search.LocalSearchDataSource
import com.volokhinaleksey.datasource.search.SearchDataSource
import com.volokhinaleksey.models.remote.WordDTO

/**
 * Implementation of an interface for storing values obtained from some data source
 */

class SearchWordsRepositoryImpl(
    private val remoteDataSource: SearchDataSource,
    private val localDataSource: LocalSearchDataSource
) : SearchWordsRepository {

    /**
     * A method for getting a list of words by word from some data source
     * @param word - The word you need to search for and get all the words with a similar spelling
     * @param isRemoteSource - Boolean value for determining the data source
     */

    override suspend fun getWordsData(word: String, isRemoteSource: Boolean): List<WordDTO> {
        return if (isRemoteSource) {
            val wordsList = remoteDataSource.getWordsData(word = word)
            localDataSource.saveWordToDB(word = wordsList)
            wordsList
        } else {
            localDataSource.getWordsData(word = word)
        }
    }

}