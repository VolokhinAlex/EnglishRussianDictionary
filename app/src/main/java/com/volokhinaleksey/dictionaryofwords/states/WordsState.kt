package com.volokhinaleksey.dictionaryofwords.states

import com.volokhinaleksey.dictionaryofwords.model.WordData
import com.volokhinaleksey.dictionaryofwords.states.WordsState.*

/**
 * States that come from the remote server. Total 3 states
 * 1. [Success] - If the request is successful, stores a list of words
 * 2. [Error] - If the request is executed unsuccessfully, stores an error.
 * 3. [Loading] - If the request is still being executed.
 */

sealed class WordsState {
    data class Success(val wordData: List<WordData>) : WordsState()
    data class Error(val error: Throwable) : WordsState()
    object Loading : WordsState()
}