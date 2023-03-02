package com.volokhinaleksey.dictionaryofwords.interactor.description

import com.volokhinaleksey.dictionaryofwords.repository.meanings.MeaningsRepository
import com.volokhinaleksey.dictionaryofwords.states.MeaningsState

/**
 * Implementation of an interface that describes the logic of the application that does not depend on the
 *  framework and platform in any way
 */

class WordDescriptionInteractorImpl(
    private val repository: MeaningsRepository
) : WordDescriptionInteractor<MeaningsState> {

    /**
     * A method for getting the meanings of a word from some source
     * @param meaningId - ID of the word meaning
     * @param isRemoteSource - Boolean value for determining the data source
     */

    override suspend fun getMeaningsData(meaningId: Long, isRemoteSource: Boolean): MeaningsState {
        val meaningsState = MeaningsState.Success(
            repository.getMeaningsData(
                meaningId = meaningId,
                isRemoteSource = isRemoteSource
            )
        )
        return meaningsState
    }

}