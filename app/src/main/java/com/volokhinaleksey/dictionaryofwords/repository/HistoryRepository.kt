package com.volokhinaleksey.dictionaryofwords.repository

import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO

interface HistoryRepository {

    suspend fun getHistoryData(): List<WordDTO>

}