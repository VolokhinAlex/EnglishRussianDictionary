package com.volokhinaleksey.dictionaryofwords.interactor.history

import com.volokhinaleksey.dictionaryofwords.repository.HistoryRepository
import com.volokhinaleksey.dictionaryofwords.states.WordsState

class HistoryInteractorImpl(
    private val historyRepository: HistoryRepository
) : HistoryInteractor<WordsState> {
    override suspend fun getHistoryData(): WordsState {
        return WordsState.Success(
            historyRepository.getHistoryData()
        )
    }


}