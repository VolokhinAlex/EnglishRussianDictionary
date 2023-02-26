package com.volokhinaleksey.dictionaryofwords.datasource

import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningsDataDTO
import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO

class LocalDictionaryDataSource : DictionaryDataSource {

    override suspend fun getWordsData(word: String): List<WordDTO> {
        return listOf(
            WordDTO(
                id = 1,
                text = "Something from local data Source",
                meanings = null
            )
        )
    }

    override suspend fun getMeaningsData(meaningId: Long): List<MeaningsDataDTO> {
        return listOf(MeaningsDataDTO(null, null, null, null, null, null, null, null))
    }

}