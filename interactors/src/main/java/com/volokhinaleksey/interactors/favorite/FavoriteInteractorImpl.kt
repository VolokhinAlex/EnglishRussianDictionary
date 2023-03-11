package com.volokhinaleksey.interactors.favorite

import com.volokhinaleksey.models.states.FavoriteState
import com.volokhinaleksey.repositories.favorite.FavoriteRepository

class FavoriteInteractorImpl(
    private val favoriteRepository: FavoriteRepository
) : FavoriteInteractor {
    override suspend fun getFavorites(): FavoriteState {
        return FavoriteState.Success(favoriteRepository.getFavoritesData())
    }

    override suspend fun deleteFavoriteWord(state: FavoriteState) {
        when (state) {
            is FavoriteState.Success -> {
                favoriteRepository.deleteFavoriteWord(word = state.favoriteWord[0])
            }

            else -> Unit
        }
    }

}