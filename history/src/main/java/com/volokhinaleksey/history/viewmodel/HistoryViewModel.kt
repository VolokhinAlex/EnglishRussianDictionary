package com.volokhinaleksey.history.viewmodel


import com.volokhinaleksey.core.viewmodel.BaseViewModel
import com.volokhinaleksey.interactors.history.HistoryInteractor
import com.volokhinaleksey.models.states.WordsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel for the screen of search history.
 */

class HistoryViewModel(
    private val interactor: HistoryInteractor<WordsState>
) : BaseViewModel<WordsState>() {

    init {
        getHistory()
    }

    /**
     * Method for getting the history of the list of words
     */

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