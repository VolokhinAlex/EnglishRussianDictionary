package com.volokhinaleksey.history.viewmodel


import com.volokhinaleksey.core.viewmodel.BaseViewModel
import com.volokhinaleksey.interactors.history.HistoryInteractor
import com.volokhinaleksey.models.states.WordsState
import kotlinx.coroutines.CoroutineExceptionHandler
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
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            currentMutableData.postValue(WordsState.Error(throwable))
        }) {
            currentMutableData.postValue(WordsState.Loading)
            val requestResponse = interactor.getHistoryData()
            currentMutableData.postValue(requestResponse)
        }
    }

}