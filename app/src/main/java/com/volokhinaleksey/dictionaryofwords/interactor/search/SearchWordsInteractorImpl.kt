package com.volokhinaleksey.dictionaryofwords.interactor.search

import com.volokhinaleksey.dictionaryofwords.repository.SearchWordsRepository
import com.volokhinaleksey.dictionaryofwords.states.WordsState

class SearchWordsInteractorImpl(
    private val repository: SearchWordsRepository
) : SearchWordsInteractor<WordsState> {

    override suspend fun getWordsData(word: String, isRemoteSource: Boolean): WordsState {
        return WordsState.Success(
            repository.getWordsData(
                word = word,
                isRemoteSource = isRemoteSource
            )
        )
    }

}