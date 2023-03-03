package com.volokhinaleksey.models.states

import com.volokhinaleksey.models.remote.WordDTO
import com.volokhinaleksey.models.states.WordsState.*

/**
 * States that come from the remote server. Total 3 states
 * 1. [Success] - If the request is successful, stores a list of words
 * 2. [Error] - If the request is executed unsuccessfully, stores an error.
 * 3. [Loading] - If the request is still being executed.
 */

sealed class WordsState {
    data class Success(val wordData: List<WordDTO>) : WordsState()
    data class Error(val error: Throwable) : WordsState()
    object Loading : WordsState()
}