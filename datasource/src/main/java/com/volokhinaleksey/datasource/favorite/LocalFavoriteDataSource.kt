package com.volokhinaleksey.datasource.favorite

import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.models.local.FavoriteEntity
import com.volokhinaleksey.models.remote.FavoriteWord
import com.volokhinaleksey.mapperutils.mapMeaningsEntityToMeaningsList

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

    override suspend fun deleteFavoriteWord(word: FavoriteWord) {
        database.favoriteDao().delete(mapFavoriteWordToFavoriteEntity(favoriteWord = word))
    }

}

fun mapFavoriteWordToFavoriteEntity(favoriteWord: FavoriteWord): FavoriteEntity =
    FavoriteEntity(
        wordId = favoriteWord.wordId,
        isFavorite = favoriteWord.isFavorite,
        word = favoriteWord.word
    )