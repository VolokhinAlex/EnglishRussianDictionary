package com.volokhinaleksey.dictionaryofwords.viewmodel

import com.volokhinaleksey.dictionaryofwords.interactor.description.WordDescriptionInteractor
import com.volokhinaleksey.dictionaryofwords.states.MeaningsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordDescriptionViewModel(
    private val interactor: WordDescriptionInteractor<MeaningsState>
) : BaseViewModel<MeaningsState>() {

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

}