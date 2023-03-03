package com.volokhinaleksey.dictionaryofwords.datasource

import com.volokhinaleksey.dictionaryofwords.model.WordData

interface DictionaryDataSource {
    suspend fun getWordsData(word: String): List<WordData>
}