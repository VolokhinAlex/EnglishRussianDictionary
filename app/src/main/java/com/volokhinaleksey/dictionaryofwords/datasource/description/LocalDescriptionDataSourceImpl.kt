package com.volokhinaleksey.dictionaryofwords.datasource.description

import com.volokhinaleksey.dictionaryofwords.model.remote.FavoriteWord
import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningDTO
import com.volokhinaleksey.dictionaryofwords.room.database.DictionaryDatabase
import com.volokhinaleksey.dictionaryofwords.utils.mapFavoriteWordToFavoriteEntity
import com.volokhinaleksey.dictionaryofwords.utils.mapMeaningsEntityToMeaningsList
import com.volokhinaleksey.dictionaryofwords.utils.mapMeaningsListToMeaningsEntity

class LocalDescriptionDataSourceImpl(
    private val database: DictionaryDatabase
) : LocalDescriptionDataSource {

    override suspend fun saveWordToDB(meaningDTO: List<MeaningDTO>) {
        database.meaningDao().insert(mapMeaningsListToMeaningsEntity(meaningDTO))
    }

    override suspend fun saveFavoriteWord(favoriteWord: FavoriteWord) {
        database.favoriteDao().insert(mapFavoriteWordToFavoriteEntity(favoriteWord))
    }

    override suspend fun getFavoriteWordFlag(wordId: Long): FavoriteWord? {
        val favoriteWord = database.favoriteDao().getFavoriteWord(wordId = wordId)
        return favoriteWord?.let {
            FavoriteWord(
                wordId = favoriteWord.wordId,
                word = favoriteWord.word,
                isFavorite = favoriteWord.isFavorite,
                meanings = mapMeaningsEntityToMeaningsList(
                    database.meaningDao().getWordMeaningByWordId(wordId = favoriteWord.wordId)
                )
            )
        }
    }

    /**
     * Method for getting a list of word meanings from a local data source.
     * @param meaningId - ID of the word value to expand the list of values for
     */

    override suspend fun getMeaningsData(meaningId: Long): List<MeaningDTO> {
        return mapMeaningsEntityToMeaningsList(
            database.meaningDao().getWordMeaningById(meaningId = meaningId)
        )
    }
}