package com.volokhinaleksey.datasource.favorite

import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.mapperutils.mapMeaningDtoToMeaningUI
import com.volokhinaleksey.mapperutils.mapMeaningsEntityToMeaningsList
import com.volokhinaleksey.models.local.FavoriteEntity
import com.volokhinaleksey.models.ui.FavoriteWord

class LocalFavoriteDataSource(
    private val database: DictionaryDatabase
) : FavoriteDataSource {
    override suspend fun getFavorites(): List<FavoriteWord> {
        return database.favoriteDao().getFavoriteWords().map {
            val meanings = database.meaningDao().getWordMeaningByWordId(wordId = it.wordId)
            FavoriteWord(
                wordId = it.wordId,
                word = it.word,
                isFavorite = it.isFavorite,
                meanings = mapMeaningsEntityToMeaningsList(
                    meaningEntity = meanings,
                    exampleEntity = meanings.flatMap {
                        database.exampleDao().getExampleWordById(meaningId = it.id)
                    },
                    similarTranslationEntity = meanings.flatMap {
                        database.similarTranslationDao()
                            .getSimilarTranslationById(meaningId = it.id)
                    }
                ).map {
                    mapMeaningDtoToMeaningUI(it)
                }
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