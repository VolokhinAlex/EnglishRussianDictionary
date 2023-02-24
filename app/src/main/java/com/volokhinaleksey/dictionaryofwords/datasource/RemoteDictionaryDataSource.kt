package com.volokhinaleksey.dictionaryofwords.datasource

import com.volokhinaleksey.dictionaryofwords.model.WordData
import com.volokhinaleksey.dictionaryofwords.repository.ApiHolder

class RemoteDictionaryDataSource (
    private val apiHolder: ApiHolder
) : DictionaryDataSource {

    override suspend fun getWordsData(word: String): List<WordData> {
        return apiHolder.apiService.getWordsBySearch(wordToSearch = word)
    }

}