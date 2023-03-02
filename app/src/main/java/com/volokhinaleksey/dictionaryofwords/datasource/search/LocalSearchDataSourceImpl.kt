package com.volokhinaleksey.dictionaryofwords.datasource.search

import com.volokhinaleksey.dictionaryofwords.model.local.HistoryEntity
import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO
import com.volokhinaleksey.dictionaryofwords.room.database.DictionaryDatabase
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import com.volokhinaleksey.dictionaryofwords.utils.mapListWordDTOToListWordEntity
import com.volokhinaleksey.dictionaryofwords.utils.mapListWordDTOToMeaningEntityList
import com.volokhinaleksey.dictionaryofwords.utils.mapMeaningsEntityToMeaningsList
import com.volokhinaleksey.dictionaryofwords.utils.mapWordDTOToHistoryEntity

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
                if (searchResult.isEmpty() || searchResult[0].text.isNullOrEmpty()) {
                    error("Empty data")
                } else {
                    db.wordDao().insert(wordEntity = mapListWordDTOToListWordEntity(searchResult))
                    db.historyDao().insert(mapWordDTOToHistoryEntity(wordDTO = searchResult[0]))
                    searchResult.forEach { word ->
                        db.meaningDao().insert(
                            mapListWordDTOToMeaningEntityList(
                                meaningDTO = word.meanings.orEmpty(),
                                wordId = word.id ?: 0
                            )
                        )
                    }
                    HistoryEntity(
                        word = searchResult[0].text.orEmpty(),
                        wordId = searchResult[0].id ?: 0
                    )
                }
            }

            else -> Unit
        }
    }
}