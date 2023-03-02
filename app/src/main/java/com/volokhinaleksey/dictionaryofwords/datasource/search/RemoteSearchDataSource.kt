package com.volokhinaleksey.dictionaryofwords.datasource.search

import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO
import com.volokhinaleksey.dictionaryofwords.repository.ApiHolder

class RemoteSearchDataSource(
    private val apiHolder: ApiHolder
) : SearchDataSource {
    /**
     * Method for getting a list of words by word from a remote data source
     * @param word - The word for which you need to get a list of words
     */

    override suspend fun getWordsData(word: String): List<WordDTO> {
        return apiHolder.apiService.getWordsBySearch(wordToSearch = word)
    }

}