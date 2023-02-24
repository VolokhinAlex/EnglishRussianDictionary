package com.volokhinaleksey.dictionaryofwords.datasource

import com.volokhinaleksey.dictionaryofwords.model.WordData

class LocalDictionaryDataSource : DictionaryDataSource {

    override suspend fun getWordsData(word: String): List<WordData> {
        return listOf(
            WordData(
                text = "Something from local data Source",
                meanings = null
            )
        )
    }

}