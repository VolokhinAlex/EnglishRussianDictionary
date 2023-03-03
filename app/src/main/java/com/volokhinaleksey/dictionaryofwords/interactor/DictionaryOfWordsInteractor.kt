package com.volokhinaleksey.dictionaryofwords.interactor

import com.volokhinaleksey.dictionaryofwords.model.WordData
import com.volokhinaleksey.dictionaryofwords.repository.Repository
import com.volokhinaleksey.dictionaryofwords.states.WordsState

class DictionaryOfWordsInteractor(
    private val repository: Repository<List<WordData>>
) : Interactor<WordsState> {

    override suspend fun getWordsData(word: String, isRemoteSource: Boolean): WordsState {
        return WordsState.Success(repository.getWordsData(word, isRemoteSource))
    }

}