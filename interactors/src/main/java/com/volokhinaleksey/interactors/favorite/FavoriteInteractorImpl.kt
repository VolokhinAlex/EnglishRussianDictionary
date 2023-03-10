package com.volokhinaleksey.interactors.favorite

import com.volokhinaleksey.models.states.FavoriteState
import com.volokhinaleksey.repositories.favorite.FavoriteRepository

/**
 * Implementation of an interface that describes the logic of the application that does not depend on the
 *  framework and platform in any way
 */

class FavoriteInteractorImpl(
    private val favoriteRepository: FavoriteRepository
) : FavoriteInteractor {

    /**
     * A method for getting selected words from some data source
     */

    override suspend fun getFavorites(): FavoriteState {
        return FavoriteState.Success(favoriteRepository.getFavoritesData())
    }

    /**
     * Method for deleting a favorite word
     */

    override suspend fun deleteFavoriteWord(state: FavoriteState) {
        when (state) {
            is FavoriteState.Success -> {
                favoriteRepository.deleteFavoriteWord(word = state.favoriteWord[0])
            }

            else -> Unit
        }
    }

}