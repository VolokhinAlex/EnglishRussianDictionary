package com.volokhinaleksey.repositories.history

import com.volokhinaleksey.models.remote.WordDTO

interface HistoryRepository {

    suspend fun getHistoryData(): List<WordDTO>

}