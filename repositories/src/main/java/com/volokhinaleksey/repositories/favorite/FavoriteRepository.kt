package com.volokhinaleksey.repositories.favorite

import com.volokhinaleksey.models.ui.FavoriteWord

/**
 * Interface for a repository of values obtained from some data source
 */

interface FavoriteRepository {

    /**
     * Method for getting a list of favorite words
     */

    suspend fun getFavoritesData(): List<FavoriteWord>

    /**
     * Method for deleting a favorite word
     * @param word - The word to remove from favorites
     */

    suspend fun deleteFavoriteWord(word: FavoriteWord)

}