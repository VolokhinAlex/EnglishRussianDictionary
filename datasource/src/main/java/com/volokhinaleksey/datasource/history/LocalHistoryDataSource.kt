package com.volokhinaleksey.datasource.history

import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.mapperutils.mapMeaningsEntityToMeaningsList
import com.volokhinaleksey.models.remote.WordDTO

class LocalHistoryDataSource(
    private val database: DictionaryDatabase
) : HistoryDataSource {

    /**
     * Method for getting a list of words history
     */

    override suspend fun getHistoryData(): List<WordDTO> {
        return database.historyDao().all().map { historyEntity ->
            val meanings = database.meaningDao()
                .getWordMeaningByWordId(wordId = historyEntity.wordId)
            WordDTO(
                id = historyEntity.wordId,
                text = historyEntity.word,
                meanings = mapMeaningsEntityToMeaningsList(
                    meaningEntity = meanings,
                    exampleEntity = meanings.flatMap {
                        database.exampleDao().getExampleWordById(meaningId = it.id)
                    },
                    similarTranslationEntity = meanings.flatMap {
                        database.similarTranslationDao()
                            .getSimilarTranslationById(meaningId = it.id)
                    }
                )
            )
        }
    }

}