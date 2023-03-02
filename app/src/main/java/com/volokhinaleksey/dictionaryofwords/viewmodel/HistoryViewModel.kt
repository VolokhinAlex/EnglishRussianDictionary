package com.volokhinaleksey.dictionaryofwords.viewmodel

import com.volokhinaleksey.dictionaryofwords.interactor.history.HistoryInteractor
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val interactor: HistoryInteractor<WordsState>
) : BaseViewModel<WordsState>() {

    fun getHistory() {
        viewModelScope.launch {
            currentMutableData.emit(WordsState.Loading)
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val requestResponse =
                        interactor.getHistoryData()
                    currentMutableData.emit(requestResponse)
                } catch (exception: Exception) {
                    currentMutableData.emit(WordsState.Error(exception))
                }
            }
        }
    }

}