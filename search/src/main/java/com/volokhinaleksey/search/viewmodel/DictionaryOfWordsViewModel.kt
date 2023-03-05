package com.volokhinaleksey.search.viewmodel

import com.volokhinaleksey.interactors.search.SearchWordsInteractor
import com.volokhinaleksey.models.states.WordsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel for getting words for a dictionary by searching for a word
 */

class DictionaryOfWordsViewModel(
    private val interactor: SearchWordsInteractor<WordsState>
) : com.volokhinaleksey.core.viewmodel.BaseViewModel<WordsState>() {

    /**
     * The method searches for the meanings of a word by some word
     * @param word - The word whose meanings need to be found
     * @param isOnline - Is there internet on the user's device or not
     */

    fun getWordMeanings(word: String, isOnline: Boolean) {
        viewModelScope.launch {
            currentMutableData.emit(WordsState.Loading)
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val requestResponse =
                        interactor.getWordsData(word = word, isRemoteSource = isOnline)
                    currentMutableData.emit(requestResponse)
                } catch (exception: Exception) {
                    currentMutableData.emit(WordsState.Error(exception))
                }
            }
        }
    }

}