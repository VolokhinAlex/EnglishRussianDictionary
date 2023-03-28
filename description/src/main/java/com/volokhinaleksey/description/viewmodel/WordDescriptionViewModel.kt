package com.volokhinaleksey.description.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.volokhinaleksey.core.viewmodel.BaseViewModel
import com.volokhinaleksey.interactors.description.WordDescriptionInteractor
import com.volokhinaleksey.models.states.FavoriteState
import com.volokhinaleksey.models.states.MeaningsState
import com.volokhinaleksey.models.ui.FavoriteWord
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * ViewModel for more information about the word for the details screen
 */

class WordDescriptionViewModel(
    private val interactor: WordDescriptionInteractor,
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel<MeaningsState>() {

    private val mutableFavoriteData: MutableLiveData<FavoriteState> = MutableLiveData()
    val favoriteData: LiveData<FavoriteState> = mutableFavoriteData

    /**
     * Method for getting a list of word meanings
     * @param meaningId - Id of the word meaning
     * @param isOnline - Boolean value for determining network availability
     */

    fun getMeanings(meaningId: Long, isOnline: Boolean) {
        currentMutableData.value = MeaningsState.Loading
        viewModelScope.launch(dispatcher + CoroutineExceptionHandler { _, throwable ->
            currentMutableData.postValue(MeaningsState.Error(throwable))
        }) {
            val responseRequest =
                interactor.getMeaningsData(meaningId = meaningId, isRemoteSource = isOnline)
            currentMutableData.postValue(responseRequest)
        }
    }

    /**
     * Method for saving a favorite word
     * @param word - The word to save
     */

    fun saveFavoriteWord(word: FavoriteWord) {
        viewModelScope.launch(dispatcher) {
            interactor.saveFavoriteWord(word = word)
        }
    }

    /**
     * Method for getting a favorite word by its id
     * @param wordId - Id of the word by which you want to get the favorite words
     */

    fun getFavoriteWord(wordId: Long) {
        mutableFavoriteData.value = FavoriteState.Loading
        viewModelScope.launch(dispatcher + CoroutineExceptionHandler { _, _ ->
            mutableFavoriteData.postValue(FavoriteState.Success(emptyList()))
        }) {
            mutableFavoriteData.postValue(interactor.getFavoriteWord(wordId = wordId))
        }
    }
}