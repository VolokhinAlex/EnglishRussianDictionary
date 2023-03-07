package com.volokhinaleksey.datasource.search

import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.mapperutils.mapListWordDTOToListWordEntity
import com.volokhinaleksey.mapperutils.mapListWordDTOToMeaningEntityList
import com.volokhinaleksey.mapperutils.mapMeaningUIToMeaningDto
import com.volokhinaleksey.mapperutils.mapMeaningsEntityToMeaningsList
import com.volokhinaleksey.mapperutils.mapWordDTOToHistoryEntity
import com.volokhinaleksey.mapperutils.mapWordUIToWordDTO
import com.volokhinaleksey.models.local.HistoryEntity
import com.volokhinaleksey.models.remote.WordDTO
import com.volokhinaleksey.models.states.WordsState

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
                    db.meaningDao().getWordMeaningByWordId(wordId = wordEntity.id)
                )
            )
        }
    }

    override suspend fun saveWordToDB(wordsState: WordsState) {
        when (wordsState) {
            is WordsState.Success -> {
                val searchResult = wordsState.wordData
                if (searchResult.isEmpty() || searchResult[0].word.isEmpty()) {
                    error("Empty data")
                } else {
                    db.wordDao().insert(
                        wordEntity = mapListWordDTOToListWordEntity(
                            searchResult.map {
                                mapWordUIToWordDTO(it)
                            }
                        )
                    )
                    db.historyDao()
                        .insert(mapWordDTOToHistoryEntity(wordDTO = mapWordUIToWordDTO(searchResult[0])))
                    searchResult.forEach { word ->
                        db.meaningDao().insert(
                            mapListWordDTOToMeaningEntityList(
                                meaningDTO = word.meanings.map { mapMeaningUIToMeaningDto(it) },
                                wordId = word.id
                            )
                        )
                    }
                    HistoryEntity(
                        word = searchResult[0].word,
                        wordId = searchResult[0].id
                    )
                }
            }

            else -> Unit
        }
    }
}