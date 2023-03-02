package com.volokhinaleksey.dictionaryofwords.datasource.favorite

import com.volokhinaleksey.dictionaryofwords.model.remote.FavoriteWord
import com.volokhinaleksey.dictionaryofwords.room.database.DictionaryDatabase
import com.volokhinaleksey.dictionaryofwords.utils.mapMeaningsEntityToMeaningsList

class LocalFavoriteDataSource(
    private val database: DictionaryDatabase
) : FavoriteDataSource {
    override suspend fun getFavorites(): List<FavoriteWord> {
        return database.favoriteDao().getFavoriteWords().map {
            FavoriteWord(
                wordId = it.wordId,
                word = it.word,
                isFavorite = it.isFavorite,
                meanings = mapMeaningsEntityToMeaningsList(
                    database.meaningDao().getWordMeaningByWordId(wordId = it.wordId)
                )
            )
        }
    }

}