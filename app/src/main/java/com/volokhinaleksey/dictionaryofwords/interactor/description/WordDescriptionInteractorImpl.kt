package com.volokhinaleksey.dictionaryofwords.interactor.description

import com.volokhinaleksey.dictionaryofwords.repository.MeaningsRepository
import com.volokhinaleksey.dictionaryofwords.states.MeaningsState

class WordDescriptionInteractorImpl(
    private val repository: MeaningsRepository
) : WordDescriptionInteractor<MeaningsState> {

    override suspend fun getMeaningsData(meaningId: Long, isRemoteSource: Boolean): MeaningsState {
        return MeaningsState.Success(
            repository.getMeaningsData(
                meaningId = meaningId,
                isRemoteSource = isRemoteSource
            )
        )
    }

}