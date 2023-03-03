package com.volokhinaleksey.interactors.history

import com.volokhinaleksey.models.states.WordsState
import com.volokhinaleksey.repositories.history.HistoryRepository

class HistoryInteractorImpl(
    private val historyRepository: HistoryRepository
) : HistoryInteractor<WordsState> {
    override suspend fun getHistoryData(): WordsState {
        return WordsState.Success(
            historyRepository.getHistoryData()
        )
    }


}