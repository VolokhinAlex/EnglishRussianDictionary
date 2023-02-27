package com.volokhinaleksey.dictionaryofwords.viewmodel

import com.volokhinaleksey.dictionaryofwords.interactor.search.SearchWordsInteractor
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * ViewModel for getting words for a dictionary by searching for a word
 */

class DictionaryOfWordsViewModel(
    private val interactor: SearchWordsInteractor<WordsState>
) : BaseViewModel<WordsState>() {

    private val searchData: MutableStateFlow<String> = MutableStateFlow("")

    /**
     * The method searches for the meanings of a word by some word
     * @param word - The word whose meanings need to be found
     * @param isOnline - Is there internet on the user's device or not
     */

    private fun getWordMeanings(word: String, isOnline: Boolean) {
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

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    fun searching(query: String, isOnline: Boolean) {
        searchData.value = query
        viewModelScope.launch {
            searchData
                .debounce(500)
                .filter {
                    return@filter !(query.isEmpty() && query.isBlank())
                }
                .flatMapLatest {
                    getWordMeanings(word = query, isOnline = isOnline)
                    searchData
                }
                .collect()
        }
    }

}