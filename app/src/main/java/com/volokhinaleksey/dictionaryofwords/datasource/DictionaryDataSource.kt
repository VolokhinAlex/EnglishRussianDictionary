package com.volokhinaleksey.dictionaryofwords.datasource

import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningsDataDTO
import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO

interface DictionaryDataSource {
    suspend fun getWordsData(word: String): List<WordDTO>

    suspend fun getMeaningsData(meaningId: Long): List<MeaningsDataDTO>
}