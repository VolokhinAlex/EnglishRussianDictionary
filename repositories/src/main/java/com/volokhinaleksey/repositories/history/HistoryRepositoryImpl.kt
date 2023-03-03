package com.volokhinaleksey.repositories.history

import com.volokhinaleksey.datasource.history.HistoryDataSource
import com.volokhinaleksey.models.remote.WordDTO

class HistoryRepositoryImpl(
    private val localDataSource: HistoryDataSource
) : HistoryRepository {
    override suspend fun getHistoryData(): List<WordDTO> {
        return localDataSource.getHistoryData()
    }

}