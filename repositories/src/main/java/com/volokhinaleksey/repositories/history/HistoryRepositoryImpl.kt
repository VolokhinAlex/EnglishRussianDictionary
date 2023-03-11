package com.volokhinaleksey.repositories.history

import com.volokhinaleksey.datasource.history.HistoryDataSource
import com.volokhinaleksey.models.remote.WordDTO

/**
 * Implementation of an interface for storing values obtained from some data source
 */

class HistoryRepositoryImpl(
    private val localDataSource: HistoryDataSource
) : HistoryRepository {

    /**
     * Method for getting search history
     */

    override suspend fun getHistoryData(): List<WordDTO> {
        return localDataSource.getHistoryData()
    }

}