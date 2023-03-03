package com.volokhinaleksey.datasource.history

import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.models.remote.WordDTO
import com.volokhinaleksey.mapperutils.mapMeaningsEntityToMeaningsList

class LocalHistoryDataSource(
    private val database: DictionaryDatabase
) : HistoryDataSource {

    override suspend fun getHistoryData(): List<WordDTO> {
        return database.historyDao().all().map { historyEntity ->
            WordDTO(
                id = historyEntity.wordId,
                text = historyEntity.word,
                meanings = mapMeaningsEntityToMeaningsList(
                    database.meaningDao().getWordMeaningByWordId(wordId = historyEntity.wordId)
                )
            )
        }
    }

}