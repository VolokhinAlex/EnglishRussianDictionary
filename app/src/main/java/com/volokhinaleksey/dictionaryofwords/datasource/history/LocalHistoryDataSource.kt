package com.volokhinaleksey.dictionaryofwords.datasource.history

import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO
import com.volokhinaleksey.dictionaryofwords.room.database.DictionaryDatabase
import com.volokhinaleksey.dictionaryofwords.utils.mapMeaningsEntityToMeaningsList

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