package com.volokhinaleksey.datasource.favorite

import com.volokhinaleksey.models.ui.FavoriteWord

/**
 * Interface for getting data from some data source, for a dictionary
 */

interface FavoriteDataSource {

    /**
     * Method for getting a list of favorite words
     */

    suspend fun getFavorites(): List<FavoriteWord>

    /**
     * Method for deleting a favorite word
     * @param word - Data class for the favorite word to be deleted
     */

    suspend fun deleteFavoriteWord(word: FavoriteWord)
}