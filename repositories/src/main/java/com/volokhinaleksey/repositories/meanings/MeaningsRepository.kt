package com.volokhinaleksey.repositories.meanings

import com.volokhinaleksey.models.remote.MeaningDTO
import com.volokhinaleksey.models.ui.FavoriteWord

/**
 * Interface for a repository of values obtained from some data source
 */

interface MeaningsRepository {

    /**
     * Method for getting a list of word meanings by meaning ID from some data source
     * @param meaningId - ID of the word meaning
     * @param isRemoteSource - Boolean value for determining the data source
     */

    suspend fun getMeaningsData(meaningId: Long, isRemoteSource: Boolean): List<MeaningDTO>

    /**
     * Method for saving a favorite word
     * @param word - The word to save
     */

    suspend fun saveFavoriteWord(word: FavoriteWord)

    /**
     *  A method for getting a favorite word by word id. The method returns either the selected word or null.
     *  @param wordId - Id of the word to search for favorites
     */

    suspend fun getFavoriteWord(wordId: Long): FavoriteWord?
}