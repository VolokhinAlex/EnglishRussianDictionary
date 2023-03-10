package com.volokhinaleksey.interactors.description

import com.volokhinaleksey.models.ui.FavoriteWord
import com.volokhinaleksey.models.states.FavoriteState
import com.volokhinaleksey.models.states.MeaningsState

/**
 * An interface for describing the logic of an application that does not depend on the
 * framework and platform in any way
 */

interface WordDescriptionInteractor {

    /**
     * A method for getting the meanings of a word from some source
     * @param meaningId - ID of the word meaning
     * @param isRemoteSource - Boolean value for determining the data source
     */

    suspend fun getMeaningsData(meaningId: Long, isRemoteSource: Boolean): MeaningsState

    /**
     * Method for saving a favorite word
     * @param word - The word to save
     */

    suspend fun saveFavoriteWord(word: FavoriteWord)

    /**
     * Method for getting a favorite word by its id
     * @param wordId - Word ID
     */

    suspend fun getFavoriteWord(wordId: Long): FavoriteState
}