package com.volokhinaleksey.repositories.history

import com.volokhinaleksey.models.remote.WordDTO

/**
 * Interface for a repository of values obtained from some data source
 */

interface HistoryRepository {

    /**
     * Method for getting search history
     */

    suspend fun getHistoryData(): List<WordDTO>

}