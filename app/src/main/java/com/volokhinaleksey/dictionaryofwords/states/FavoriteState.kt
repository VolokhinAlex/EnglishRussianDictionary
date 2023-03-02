package com.volokhinaleksey.dictionaryofwords.states

import com.volokhinaleksey.dictionaryofwords.model.remote.FavoriteWord
import com.volokhinaleksey.dictionaryofwords.states.FavoriteState.Error
import com.volokhinaleksey.dictionaryofwords.states.FavoriteState.Loading
import com.volokhinaleksey.dictionaryofwords.states.FavoriteState.Success

/**
 * States that come from the remote server. Total 3 states
 * 1. [Success] - If the request is successful, stores a list of favorite words
 * 2. [Error] - If the request is executed unsuccessfully, stores an error.
 * 3. [Loading] - If the request is still being executed.
 */

sealed class FavoriteState {
    data class Success(val favoriteWord: List<FavoriteWord>) : FavoriteState()
    data class Error(val error: Throwable) : FavoriteState()
    object Loading : FavoriteState()
}