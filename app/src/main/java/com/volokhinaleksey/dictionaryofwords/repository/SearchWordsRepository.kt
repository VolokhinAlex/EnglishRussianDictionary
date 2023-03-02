package com.volokhinaleksey.dictionaryofwords.repository

import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO
import com.volokhinaleksey.dictionaryofwords.states.WordsState

/**
 * Interface for a repository of values obtained from some data source
 */

interface SearchWordsRepository {

    /**
     * A method for getting a list of words by word from some data source
     * @param word - The word you need to search for and get all the words with a similar spelling
     * @param isRemoteSource - Boolean value for determining the data source
     */

    suspend fun getWordsData(word: String, isRemoteSource: Boolean): List<WordDTO>

    suspend fun saveToDB(wordState: WordsState)
}