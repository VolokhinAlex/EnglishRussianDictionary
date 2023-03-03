package com.volokhinaleksey.datasource.history

import com.volokhinaleksey.models.remote.WordDTO

interface HistoryDataSource {

    suspend fun getHistoryData(): List<WordDTO>

}