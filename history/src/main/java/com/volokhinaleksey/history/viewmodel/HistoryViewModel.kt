package com.volokhinaleksey.history.viewmodel


import com.volokhinaleksey.core.viewmodel.BaseViewModel
import com.volokhinaleksey.interactors.history.HistoryInteractor
import com.volokhinaleksey.models.states.WordsState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * ViewModel for the screen of search history.
 */

class HistoryViewModel(
    private val interactor: HistoryInteractor<WordsState>,
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel<WordsState>() {

    init {
        getHistory()
    }

    /**
     * Method for getting the history of the list of words
     */

    private fun getHistory() {
        currentMutableData.value = WordsState.Loading
        viewModelScope.launch(dispatcher + CoroutineExceptionHandler { _, throwable ->
            currentMutableData.postValue(WordsState.Error(throwable))
        }) {
            val requestResponse = interactor.getHistoryData()
            currentMutableData.postValue(requestResponse)
        }
    }

}