package com.volokhinaleksey.dictionaryofwords.repository

import com.volokhinaleksey.dictionaryofwords.datasource.history.HistoryDataSource
import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO

class HistoryRepositoryImpl(
    private val localDataSource: HistoryDataSource
) : HistoryRepository {
    override suspend fun getHistoryData(): List<WordDTO> {
        return localDataSource.getHistoryData()
    }

}