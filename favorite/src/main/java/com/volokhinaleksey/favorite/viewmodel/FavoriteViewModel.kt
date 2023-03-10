package com.volokhinaleksey.favorite.viewmodel

import com.volokhinaleksey.core.viewmodel.BaseViewModel
import com.volokhinaleksey.interactors.favorite.FavoriteInteractor
import com.volokhinaleksey.models.states.FavoriteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val favoriteInteractor: FavoriteInteractor
) : BaseViewModel<FavoriteState>() {

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            currentMutableData.emit(FavoriteState.Loading)
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val requestResponse =
                        favoriteInteractor.getFavorites()
                    currentMutableData.emit(requestResponse)
                } catch (exception: Exception) {
                    currentMutableData.emit(FavoriteState.Error(exception))
                }
            }
        }
    }

    fun deleteFavoriteWord(favoriteState: FavoriteState) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteInteractor.deleteFavoriteWord(state = favoriteState)
        }
    }

}