package com.volokhinaleksey.datasource.favorite

import com.volokhinaleksey.database.database.DictionaryDatabase
import com.volokhinaleksey.mapperutils.mapFavoriteWordToFavoriteEntity
import com.volokhinaleksey.mapperutils.mapMeaningDtoToMeaningUI
import com.volokhinaleksey.mapperutils.mapMeaningsEntityToMeaningsList
import com.volokhinaleksey.models.ui.FavoriteWord

/**
 * Implementation of the interface for receiving data from a local data source.
 */

class LocalFavoriteDataSource(
    private val database: DictionaryDatabase
) : FavoriteDataSource {

    /**
     * Method for getting a list of favorite words
     */

    override suspend fun getFavorites(): List<FavoriteWord> {
        return database.favoriteDao().getFavoriteWords().map {
            val meanings = database.meaningDao().getWordMeaningByWordId(wordId = it.wordId)
            FavoriteWord(
                wordId = it.wordId,
                word = it.word,
                isFavorite = it.isFavorite,
                meanings = mapMeaningsEntityToMeaningsList(
                    meaningEntity = meanings,
                    exampleEntity = meanings.flatMap { meaning ->
                        database.exampleDao().getExampleWordById(meaningId = meaning.id)
                    },
                    similarTranslationEntity = meanings.flatMap { meaning ->
                        database.similarTranslationDao()
                            .getSimilarTranslationById(meaningId = meaning.id)
                    }
                ).map { meaning ->
                    mapMeaningDtoToMeaningUI(meaning)
                }
            )
        }
    }

    /**
     * Method for deleting a favorite word
     * @param word - Data class for the favorite word to be deleted
     */

    override suspend fun deleteFavoriteWord(word: FavoriteWord) {
        database.favoriteDao().delete(mapFavoriteWordToFavoriteEntity(favoriteWord = word))
    }

}