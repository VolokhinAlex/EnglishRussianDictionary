package com.volokhinaleksey.datasource.history

import com.volokhinaleksey.models.remote.WordDTO

/**
 * Interface for getting data from some data source, for a dictionary
 */

interface HistoryDataSource {

    /**
     * Method for getting a list of words history
     */

    suspend fun getHistoryData(): List<WordDTO>

}