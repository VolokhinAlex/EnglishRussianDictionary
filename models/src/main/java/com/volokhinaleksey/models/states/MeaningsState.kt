package com.volokhinaleksey.models.states

import com.volokhinaleksey.models.states.MeaningsState.Error
import com.volokhinaleksey.models.states.MeaningsState.Loading
import com.volokhinaleksey.models.states.MeaningsState.Success
import com.volokhinaleksey.models.ui.Meaning

/**
 * States that come from the remote server. Total 3 states
 * 1. [Success] - If the request is successful, stores meanings of word
 * 2. [Error] - If the request is executed unsuccessfully, stores an error.
 * 3. [Loading] - If the request is still being executed.
 */

sealed class MeaningsState {
    data class Success(val wordData: List<Meaning>) : MeaningsState()
    data class Error(val error: Throwable) : MeaningsState()
    object Loading : MeaningsState()
}