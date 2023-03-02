package com.volokhinaleksey.dictionaryofwords.interactor.favorite

import com.volokhinaleksey.dictionaryofwords.states.FavoriteState

interface FavoriteInteractor {

    suspend fun getFavorites(): FavoriteState
    suspend fun deleteFavoriteWord(state: FavoriteState)

}