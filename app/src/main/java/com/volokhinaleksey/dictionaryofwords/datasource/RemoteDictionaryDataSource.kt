package com.volokhinaleksey.dictionaryofwords.datasource

import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningDTO
import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO
import com.volokhinaleksey.dictionaryofwords.repository.ApiHolder

/**
 * Implementation of the interface for receiving data from a remote data source.
 */

class RemoteDictionaryDataSource(
    private val apiHolder: ApiHolder
) : DictionaryDataSource {

    /**
     * Method for getting a list of words by word from a remote data source
     * @param word - The word for which you need to get a list of words
     */

    override suspend fun getWordsData(word: String): List<WordDTO> {
        return apiHolder.apiService.getWordsBySearch(wordToSearch = word)
    }

    /**
     * Method for getting a list of word meanings from a remote data source.
     * @param meaningId - ID of the word value to expand the list of values for
     */

    override suspend fun getMeaningsData(meaningId: Long): List<MeaningDTO> {
        return apiHolder.apiService.getMeanings(meaningId = meaningId)
    }

}