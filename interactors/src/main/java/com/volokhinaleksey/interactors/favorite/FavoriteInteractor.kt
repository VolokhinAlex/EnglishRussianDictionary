package com.volokhinaleksey.interactors.favorite

import com.volokhinaleksey.models.states.FavoriteState

interface FavoriteInteractor {

    suspend fun getFavorites(): FavoriteState
    suspend fun deleteFavoriteWord(state: FavoriteState)

}