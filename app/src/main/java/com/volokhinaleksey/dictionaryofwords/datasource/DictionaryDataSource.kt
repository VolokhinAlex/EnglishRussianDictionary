package com.volokhinaleksey.dictionaryofwords.datasource

import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningDTO
import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO

/**
 * Interface for getting data from some data source, for a dictionary
 */

interface DictionaryDataSource {

    /**
     * Method for getting a list of words by word from a data source
     * @param word - The word for which you need to get a list of words
     */

    suspend fun getWordsData(word: String): List<WordDTO>

    /**
     * Method for getting a list of word meanings.
     * @param meaningId - ID of the word value to expand the list of values for
     */

    suspend fun getMeaningsData(meaningId: Long): List<MeaningDTO>
}