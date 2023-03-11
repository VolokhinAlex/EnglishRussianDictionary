package com.volokhinaleksey.dictionaryofwords.datasource

import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningsDataDTO
import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO
import com.volokhinaleksey.dictionaryofwords.repository.ApiHolder

class RemoteDictionaryDataSource (
    private val apiHolder: ApiHolder
) : DictionaryDataSource {

    override suspend fun getWordsData(word: String): List<WordDTO> {
        return apiHolder.apiService.getWordsBySearch(wordToSearch = word)
    }

    override suspend fun getMeaningsData(meaningId: Long): List<MeaningsDataDTO> {
        return apiHolder.apiService.getMeanings(meaningId = meaningId)
    }

}