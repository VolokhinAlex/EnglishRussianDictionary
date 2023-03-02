package com.volokhinaleksey.dictionaryofwords.datasource.history

import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO

interface HistoryDataSource {

    suspend fun getHistoryData(): List<WordDTO>

}