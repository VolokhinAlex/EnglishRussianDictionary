package com.volokhinaleksey.datasource.search

import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.mapperutils.mapListWordDTOToListWordEntity
import com.volokhinaleksey.mapperutils.mapListWordDTOToMeaningEntityList
import com.volokhinaleksey.mapperutils.mapMeaningsEntityToMeaningsList
import com.volokhinaleksey.mapperutils.mapWordDTOToHistoryEntity
import com.volokhinaleksey.models.local.HistoryEntity
import com.volokhinaleksey.models.remote.WordDTO

class LocalSearchDataSourceImpl(
    private val db: DictionaryDatabase
) : LocalSearchDataSource {

    /**
     * Method for getting a list of words by word from a local data source
     * @param word - The word for which you need to get a list of words
     */

    override suspend fun getWordsData(word: String): List<WordDTO> {
        return db.wordDao().getDataByWord(word = word).map { wordEntity ->
            WordDTO(
                id = wordEntity.id,
                text = wordEntity.word,
                meanings = mapMeaningsEntityToMeaningsList(
                    meaningEntity = db.meaningDao().getWordMeaningByWordId(wordId = wordEntity.id),
                    exampleEntity = db.exampleDao().getExampleWordById(meaningId = wordEntity.id),
                    similarTranslationEntity = db.similarTranslationDao()
                        .getSimilarTranslationById(meaningId = wordEntity.id)
                )
            )
        }
    }

    /**
     * A method for saving a word to a local database
     * @param word - The word to save to the database
     */

    override suspend fun saveWordToDB(word: List<WordDTO>) {
        if (word.isEmpty() || word[0].text?.isBlank() == true) {
            error("Empty data")
        } else {
            db.wordDao().insert(
                wordEntity = mapListWordDTOToListWordEntity(word)
            )
            db.historyDao()
                .insert(mapWordDTOToHistoryEntity(wordDTO = word[0]))
            word.forEach { data ->
                db.meaningDao().insert(
                    mapListWordDTOToMeaningEntityList(
                        meaningDTO = data.meanings ?: emptyList(),
                        wordId = data.id ?: 0
                    )
                )
            }
            HistoryEntity(
                word = word[0].text.orEmpty(),
                wordId = word[0].id ?: 0
            )
        }
    }
}