package com.volokhinaleksey.favorite.viewmodel

import com.volokhinaleksey.core.viewmodel.BaseViewModel
import com.volokhinaleksey.interactors.favorite.FavoriteInteractor
import com.volokhinaleksey.models.states.FavoriteState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch


/**
 * ViewModel for the screen of favorite words
 */

class FavoriteViewModel(
    private val favoriteInteractor: FavoriteInteractor,
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel<FavoriteState>() {

    init {
        getFavorites()
    }

    /**
     * Method for getting a list of all the favorite words
     */

    private fun getFavorites() {
        viewModelScope.launch(dispatcher + CoroutineExceptionHandler { _, throwable ->
            currentMutableData.postValue(FavoriteState.Error(throwable))
        }) {
            currentMutableData.postValue(FavoriteState.Loading)
            val requestResponse = favoriteInteractor.getFavorites()
            currentMutableData.postValue(requestResponse)
        }
    }

    /**
     * Method for deleting a favorite word
     */

    fun deleteFavoriteWord(favoriteState: FavoriteState) {
        viewModelScope.launch(dispatcher) {
            favoriteInteractor.deleteFavoriteWord(state = favoriteState)
        }
    }

}