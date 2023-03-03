package com.volokhinaleksey.dictionaryofwords.viewmodel

import com.volokhinaleksey.dictionaryofwords.interactor.Interactor
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel for getting words for a dictionary by searching for a word
 */

class DictionaryOfWordsViewModel(
    private val interactor: Interactor<WordsState>
) : BaseViewModel<WordsState>() {

    /**
     * The method searches for the meanings of a word by some word
     * @param word - The word whose meanings need to be found
     * @param isOnline - Is there internet on the user's device or not
     */

    fun getWordMeanings(word: String, isOnline: Boolean) {
        currentMutableData.value = WordsState.Loading
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            currentMutableData.postValue(WordsState.Error(throwable))
        }) {
            val requestResponse = interactor.getWordsData(word = word, isRemoteSource = isOnline)
            currentMutableData.postValue(requestResponse)
        }
    }

}