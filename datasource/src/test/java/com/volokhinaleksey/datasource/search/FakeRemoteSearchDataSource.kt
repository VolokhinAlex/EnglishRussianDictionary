package com.volokhinaleksey.datasource.search

import com.volokhinaleksey.models.remote.WordDTO

class FakeRemoteSearchDataSource : SearchDataSource {

    private val words = mutableListOf<WordDTO>()

    override suspend fun getWordsData(word: String): List<WordDTO> {
        return words.filter { it.text == word }
    }

}