package com.volokhinaleksey.interactors.search

import com.volokhinaleksey.mapperutils.mapWordDTOToWordUI
import com.volokhinaleksey.models.states.WordsState
import com.volokhinaleksey.repositories.search.SearchWordsRepository

/**
 * Implementation of an interface that describes the logic of the application that does not depend on the
 *  framework and platform in any way
 */

class SearchWordsInteractorImpl(
    private val repository: SearchWordsRepository
) : SearchWordsInteractor<WordsState> {

    /**
     * A method for getting the words by word from some source
     * @param word - The word for which you need to get words from the source
     * @param isRemoteSource - Boolean value for determining the data source
     */

    override suspend fun getWordsData(word: String, isRemoteSource: Boolean): WordsState {
        return WordsState.Success(
            repository.getWordsData(
                word = word,
                isRemoteSource = isRemoteSource
            ).map { mapWordDTOToWordUI(it) }
        )
    }

}