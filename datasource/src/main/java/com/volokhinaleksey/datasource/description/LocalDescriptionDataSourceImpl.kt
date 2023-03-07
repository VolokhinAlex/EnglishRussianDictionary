package com.volokhinaleksey.datasource.description

import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.mapperutils.mapFavoriteWordToFavoriteEntity
import com.volokhinaleksey.mapperutils.mapMeaningDtoToMeaningUI
import com.volokhinaleksey.mapperutils.mapMeaningsEntityToMeaningsList
import com.volokhinaleksey.mapperutils.mapMeaningsListToMeaningsEntity
import com.volokhinaleksey.models.ui.FavoriteWord

class LocalDescriptionDataSourceImpl(
    private val database: DictionaryDatabase
) : LocalDescriptionDataSource {

    override suspend fun saveWordToDB(meaningDTO: List<com.volokhinaleksey.models.remote.MeaningDTO>) {
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
                ).map {
                    mapMeaningDtoToMeaningUI(it)
                }
            )
        }
    }

    /**
     * Method for getting a list of word meanings from a local data source.
     * @param meaningId - ID of the word value to expand the list of values for
     */

    override suspend fun getMeaningsData(meaningId: Long): List<com.volokhinaleksey.models.remote.MeaningDTO> {
        return mapMeaningsEntityToMeaningsList(
            database.meaningDao().getWordMeaningById(meaningId = meaningId)
        )
    }
}