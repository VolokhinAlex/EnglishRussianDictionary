package com.volokhinaleksey.dictionaryofwords.interactor.favorite

import com.volokhinaleksey.dictionaryofwords.repository.favorite.FavoriteRepository
import com.volokhinaleksey.dictionaryofwords.states.FavoriteState

class FavoriteInteractorImpl(
    private val favoriteRepository: FavoriteRepository
) : FavoriteInteractor {
    override suspend fun getFavorites(): FavoriteState {
        return FavoriteState.Success(favoriteRepository.getFavoritesData())
    }

}