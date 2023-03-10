package com.volokhinaleksey.repositories.search

import com.volokhinaleksey.models.remote.WordDTO

class FakeSearchWordsRepository : SearchWordsRepository{

    private val listData = mutableListOf<WordDTO>()

    override suspend fun getWordsData(word: String, isRemoteSource: Boolean): List<WordDTO> {
        TODO("Not yet implemented")
    }

}