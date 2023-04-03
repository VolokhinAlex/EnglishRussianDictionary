package com.volokhinaleksey.search.viewmodel

import com.volokhinaleksey.core.viewmodel.BaseViewModel
import com.volokhinaleksey.interactors.search.SearchWordsInteractor
import com.volokhinaleksey.models.states.WordsState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * ViewModel for getting words for a dictionary by searching for a word
 */

class DictionaryOfWordsViewModel(
    private val interactor: SearchWordsInteractor<WordsState>,
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel<WordsState>() {

    /**
     * The method searches for the meanings of a word by some word
     * @param word - The word whose meanings need to be found
     * @param isOnline - Is there internet on the user's device or not
     */

    fun getWordMeanings(word: String, isOnline: Boolean) {
        currentMutableData.value = WordsState.Loading
        viewModelScope.launch(dispatcher + CoroutineExceptionHandler { _, throwable ->
            currentMutableData.postValue(WordsState.Error(throwable))
        }) {
            val requestResponse = interactor.getWordsData(word = word, isRemoteSource = isOnline)
            currentMutableData.postValue(requestResponse)
        }
    }

}