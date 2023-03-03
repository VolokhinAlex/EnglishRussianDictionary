package com.volokhinaleksey.dictionaryofwords.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.volokhinaleksey.dictionaryofwords.interactor.favorite.FavoriteInteractor
import com.volokhinaleksey.dictionaryofwords.states.FavoriteState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val favoriteInteractor: FavoriteInteractor
) : BaseViewModel<FavoriteState>() {

    private val _favoriteData: MutableLiveData<FavoriteState> = MutableLiveData()
    val favoriteData: LiveData<FavoriteState> = _favoriteData

    fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            _favoriteData.value = FavoriteState.Error(throwable)
        }) {
            val requestResponse = favoriteInteractor.getFavorites()
            _favoriteData.postValue(requestResponse)
        }
    }

    fun deleteFavoriteWord(favoriteState: FavoriteState) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteInteractor.deleteFavoriteWord(state = favoriteState)
        }
    }

}