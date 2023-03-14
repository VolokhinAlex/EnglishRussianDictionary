package com.volokhinaleksey.datasource.description

import com.volokhinaleksey.models.remote.MeaningDTO
import com.volokhinaleksey.models.ui.FavoriteWord

interface LocalDescriptionDataSource : DescriptionDataSource {

    /**
     * Method for saving a word to a local database
     * @param meaningDTO - Data class for the meanings of a word to be saved
     */

    suspend fun saveWordToDB(meaningDTO: List<MeaningDTO>)

    /**
     * A method for saving a favorite word to a local database
     * @param favoriteWord - Data class for the favorite word to be saved
     */

    suspend fun saveFavoriteWord(favoriteWord: FavoriteWord)

    /**
     * Method for getting a favorite word by word id
     * @param wordId - Word id for getting a favorite word
     */

    suspend fun getFavoriteWord(wordId: Long): FavoriteWord?
}