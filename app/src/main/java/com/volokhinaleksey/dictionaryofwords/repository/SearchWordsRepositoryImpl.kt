package com.volokhinaleksey.dictionaryofwords.repository

import com.volokhinaleksey.dictionaryofwords.datasource.DictionaryDataSource
import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO

/**
 * Implementation of an interface for storing values obtained from some data source
 */

class SearchWordsRepositoryImpl(
    private val remoteDataSource: DictionaryDataSource,
    private val localDataSource: DictionaryDataSource
) : SearchWordsRepository {

    /**
     * A method for getting a list of words by word from some data source
     * @param word - The word you need to search for and get all the words with a similar spelling
     * @param isRemoteSource - Boolean value for determining the data source
     */

    override suspend fun getWordsData(word: String, isRemoteSource: Boolean): List<WordDTO> {
        return if (isRemoteSource) {
            remoteDataSource.getWordsData(word = word)
        } else {
            localDataSource.getWordsData(word = word)
        }
    }

}