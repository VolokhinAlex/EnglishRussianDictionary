package com.volokhinaleksey.interactors.favorite

import com.volokhinaleksey.models.states.FavoriteState

/**
 * An interface for describing the logic of an application that does not depend on the
 * framework and platform in any way
 */

interface FavoriteInteractor {

    /**
     * A method for getting selected words from some data source
     */

    suspend fun getFavorites(): FavoriteState

    /**
     * Method for deleting a favorite word
     */

    suspend fun deleteFavoriteWord(state: FavoriteState)

}