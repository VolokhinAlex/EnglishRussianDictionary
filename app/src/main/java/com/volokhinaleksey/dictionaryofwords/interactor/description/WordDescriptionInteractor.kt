package com.volokhinaleksey.dictionaryofwords.interactor.description

import com.volokhinaleksey.dictionaryofwords.model.remote.FavoriteWord
import com.volokhinaleksey.dictionaryofwords.states.FavoriteState
import com.volokhinaleksey.dictionaryofwords.states.MeaningsState

/**
 * An interface for describing the logic of an application that does not depend on the
 * framework and platform in any way
 */

interface WordDescriptionInteractor {

    /**
     * A method for getting the meanings of a word from some source
     * @param meaningId - ID of the word meaning
     * @param isRemoteSource - Boolean value for determining the data source
     */

    suspend fun getMeaningsData(meaningId: Long, isRemoteSource: Boolean): MeaningsState

    suspend fun saveFavoriteWord(word: FavoriteWord)

    suspend fun getFavoriteWord(wordId: Long): FavoriteState
}