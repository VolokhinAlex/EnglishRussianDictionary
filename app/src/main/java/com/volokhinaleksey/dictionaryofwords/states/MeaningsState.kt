package com.volokhinaleksey.dictionaryofwords.states

import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningDTO
import com.volokhinaleksey.dictionaryofwords.states.MeaningsState.*

/**
 * States that come from the remote server. Total 3 states
 * 1. [Success] - If the request is successful, stores meanings of word
 * 2. [Error] - If the request is executed unsuccessfully, stores an error.
 * 3. [Loading] - If the request is still being executed.
 */

sealed class MeaningsState {
    data class Success(val wordData: List<MeaningDTO>) : MeaningsState()
    data class Error(val error: Throwable) : MeaningsState()
    object Loading : MeaningsState()
}