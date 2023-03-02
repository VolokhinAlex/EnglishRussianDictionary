package com.volokhinaleksey.dictionaryofwords.viewmodel

import com.volokhinaleksey.dictionaryofwords.interactor.description.WordDescriptionInteractor
import com.volokhinaleksey.dictionaryofwords.model.remote.FavoriteWord
import com.volokhinaleksey.dictionaryofwords.states.MeaningsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel for more information about the word for the details screen
 */

class WordDescriptionViewModel(
    private val interactor: WordDescriptionInteractor<MeaningsState>
) : BaseViewModel<MeaningsState>() {

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

}