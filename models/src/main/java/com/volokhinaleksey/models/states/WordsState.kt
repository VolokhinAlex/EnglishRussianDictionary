package com.volokhinaleksey.models.states

import com.volokhinaleksey.models.states.WordsState.Error
import com.volokhinaleksey.models.states.WordsState.Loading
import com.volokhinaleksey.models.states.WordsState.Success
import com.volokhinaleksey.models.ui.Word

/**
 * States that come from the remote server. Total 3 states
 * 1. [Success] - If the request is successful, stores a list of words
 * 2. [Error] - If the request is executed unsuccessfully, stores an error.
 * 3. [Loading] - If the request is still being executed.
 */

sealed interface WordsState {
    data class Success(val wordData: List<Word>) : WordsState
    data class Error(val error: Throwable) : WordsState
    object Loading : WordsState
}