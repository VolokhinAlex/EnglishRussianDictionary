package com.volokhinaleksey.description.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.volokhinaleksey.core.viewmodel.BaseViewModel
import com.volokhinaleksey.interactors.description.WordDescriptionInteractor
import com.volokhinaleksey.models.states.FavoriteState
import com.volokhinaleksey.models.states.MeaningsState
import com.volokhinaleksey.models.ui.FavoriteWord
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel for more information about the word for the details screen
 */

class WordDescriptionViewModel(
    private val interactor: WordDescriptionInteractor
) : BaseViewModel<MeaningsState>() {

    private val mutableFavoriteData: MutableLiveData<FavoriteState> = MutableLiveData()
    val favoriteData: LiveData<FavoriteState> = mutableFavoriteData

    /**
     * Method for getting a list of word meanings
     * @param meaningId - Id of the word meaning
     * @param isOnline - Boolean value for determining network availability
     */

    fun getMeanings(meaningId: Long, isOnline: Boolean) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            viewModelScope.launch {
                currentMutableData.emit(MeaningsState.Error(throwable))
            }
        }) {
            currentMutableData.emit(MeaningsState.Loading)
            val responseRequest =
                interactor.getMeaningsData(meaningId = meaningId, isRemoteSource = isOnline)
            currentMutableData.emit(responseRequest)
        }
    }

    fun saveFavoriteWord(word: FavoriteWord) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.saveFavoriteWord(word = word)
        }
    }

    fun getFavoriteWord(wordId: Long) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, _ ->
            mutableFavoriteData.postValue(FavoriteState.Success(emptyList()))
        }) {
            mutableFavoriteData.postValue(interactor.getFavoriteWord(wordId = wordId))
        }
    }
}