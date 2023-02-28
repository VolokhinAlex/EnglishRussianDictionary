package com.volokhinaleksey.dictionaryofwords.datasource

import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningDTO
import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO

/**
 * Implementation of an interface for getting data from a local data source.
 * For example from a local database
 */

class LocalDictionaryDataSource : DictionaryDataSource {

    /**
     * Method for getting a list of words by word from a local data source
     * @param word - The word for which you need to get a list of words
     */

    override suspend fun getWordsData(word: String): List<WordDTO> {
        return listOf(
            WordDTO(
                id = 1,
                text = "Something from local data Source",
                meanings = null
            )
        )
    }

    /**
     * Method for getting a list of word meanings from a local data source.
     * @param meaningId - ID of the word value to expand the list of values for
     */

    override suspend fun getMeaningsData(meaningId: Long): List<MeaningDTO> {
        return listOf(MeaningDTO(null, null, null, null, null, null, null, null, null))
    }

}