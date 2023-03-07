package com.volokhinaleksey.history.viewmodel


import com.volokhinaleksey.core.viewmodel.BaseViewModel
import com.volokhinaleksey.interactors.history.HistoryInteractor
import com.volokhinaleksey.models.states.WordsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val interactor: HistoryInteractor<WordsState>
) : BaseViewModel<WordsState>() {

    init {
        getHistory()
    }

    private fun getHistory() {
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