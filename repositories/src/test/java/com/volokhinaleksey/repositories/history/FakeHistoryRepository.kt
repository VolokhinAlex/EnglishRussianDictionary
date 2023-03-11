package com.volokhinaleksey.repositories.history

import com.volokhinaleksey.models.remote.WordDTO

class FakeHistoryRepository : HistoryRepository {

    private val historyWords: MutableList<WordDTO> = mutableListOf()

    override suspend fun getHistoryData(): List<WordDTO> {
        return historyWords
    }
}