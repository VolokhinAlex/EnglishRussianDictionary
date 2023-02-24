package com.volokhinaleksey.dictionaryofwords.viewmodel

import com.volokhinaleksey.dictionaryofwords.interactor.Interactor
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class DictionaryOfWordsViewModel(
    private val interactor: Interactor<WordsState>
) : BaseViewModel<WordsState>() {

    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun getWordMeanings(word: String, isOnline: Boolean) {
        viewModelScope.launch {
            try {
                val request = interactor.getWordsData(word = word, isRemoteSource = isOnline)
                currentMutableData.value = request
            } catch (e: java.lang.Exception) {
                currentMutableData.value = WordsState.Error(e)
            }
        }
    }
}