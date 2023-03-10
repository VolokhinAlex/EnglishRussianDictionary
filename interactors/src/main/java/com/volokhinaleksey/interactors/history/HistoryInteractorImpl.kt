package com.volokhinaleksey.interactors.history

import com.volokhinaleksey.mapperutils.mapWordDTOToWordUI
import com.volokhinaleksey.models.states.WordsState
import com.volokhinaleksey.repositories.history.HistoryRepository

/**
 * Implementation of an interface that describes the logic of the application that does not depend on the
 *  framework and platform in any way
 */

class HistoryInteractorImpl(
    private val historyRepository: HistoryRepository
) : HistoryInteractor<WordsState> {

    /**
     * A method for getting search history from some data source
     */

    override suspend fun getHistoryData(): WordsState {
        return WordsState.Success(
            historyRepository.getHistoryData()
                .map { mapWordDTOToWordUI(it) })
    }

}