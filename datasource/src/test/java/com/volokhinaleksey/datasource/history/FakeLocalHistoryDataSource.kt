package com.volokhinaleksey.datasource.history

import com.volokhinaleksey.models.remote.WordDTO

class FakeLocalHistoryDataSource : HistoryDataSource {

    private val historyWords = mutableListOf<WordDTO>()

    override suspend fun getHistoryData(): List<WordDTO> {
        return historyWords
    }

}