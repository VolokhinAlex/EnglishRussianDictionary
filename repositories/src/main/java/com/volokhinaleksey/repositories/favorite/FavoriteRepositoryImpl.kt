package com.volokhinaleksey.repositories.favorite

import com.volokhinaleksey.datasource.favorite.FavoriteDataSource
import com.volokhinaleksey.models.ui.FavoriteWord

/**
 * Implementation of an interface for storing values obtained from some data source
 */

class FavoriteRepositoryImpl(
    private val favoriteDataSource: FavoriteDataSource
) : FavoriteRepository {

    /**
     * Method for getting a list of favorite words
     */

    override suspend fun getFavoritesData(): List<FavoriteWord> {
        return favoriteDataSource.getFavorites()
    }

    /**
     * Method for deleting a favorite word
     * @param word - The word to remove from favorites
     */

    override suspend fun deleteFavoriteWord(word: FavoriteWord) {
        favoriteDataSource.deleteFavoriteWord(word = word)
    }

}