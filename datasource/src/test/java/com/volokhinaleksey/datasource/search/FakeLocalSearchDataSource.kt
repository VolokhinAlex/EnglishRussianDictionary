package com.volokhinaleksey.datasource.search

import com.volokhinaleksey.models.remote.WordDTO

class FakeLocalSearchDataSource : LocalSearchDataSource {

    private val words = mutableListOf<WordDTO>()

    override suspend fun saveWordToDB(word: List<WordDTO>) {
        words.addAll(word)
    }

    override suspend fun getWordsData(word: String): List<WordDTO> {
        return words.filter { it.text == word }
    }

}