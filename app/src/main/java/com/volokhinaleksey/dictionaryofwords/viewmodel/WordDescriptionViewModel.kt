package com.volokhinaleksey.dictionaryofwords.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.volokhinaleksey.interactors.description.WordDescriptionInteractor
import com.volokhinaleksey.models.remote.FavoriteWord
import com.volokhinaleksey.models.states.FavoriteState
import com.volokhinaleksey.models.states.MeaningsState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel for more information about the word for the details screen
 */

class WordDescriptionViewModel(
    private val interactor: WordDescriptionInteractor
) : com.volokhinaleksey.core.viewmodel.BaseViewModel<MeaningsState>() {

    private val mutableFavoriteData: MutableLiveData<FavoriteState> = MutableLiveData()
    val favoriteData: LiveData<FavoriteState> = mutableFavoriteData

    /**
     * Method for getting a list of word meanings
     * @param meaningId - Id of the word meaning
     * @param isOnline - Boolean value for determining network availability
     */

    fun getMeanings(meaningId: Long, isOnline: Boolean) {
        viewModelScope.launch {
            currentMutableData.emit(MeaningsState.Loading)
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val responseRequest =
                        interactor.getMeaningsData(meaningId = meaningId, isRemoteSource = isOnline)
                    currentMutableData.emit(responseRequest)
                } catch (exception: Exception) {
                    currentMutableData.emit(MeaningsState.Error(exception))
                }
            }
        }
    }

    fun saveFavoriteWord(word: FavoriteWord) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.saveFavoriteWord(word = word)
        }
    }

    fun getFavoriteWord(wordId: Long) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            mutableFavoriteData.postValue(FavoriteState.Success(emptyList()))
        }) {
            mutableFavoriteData.postValue(interactor.getFavoriteWord(wordId = wordId))
        }
    }
}