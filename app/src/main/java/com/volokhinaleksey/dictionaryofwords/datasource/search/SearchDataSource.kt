package com.volokhinaleksey.dictionaryofwords.datasource.search

import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO

/**
 * Interface for getting data from some data source, for a dictionary
 */

interface SearchDataSource {

    /**
     * Method for getting a list of words by word from a data source
     * @param word - The word for which you need to get a list of words
     */

    suspend fun getWordsData(word: String): List<WordDTO>

}