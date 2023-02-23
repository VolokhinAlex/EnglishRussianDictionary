package com.volokhinaleksey.dictionaryofwords.interactor

import com.volokhinaleksey.dictionaryofwords.model.WordData
import com.volokhinaleksey.dictionaryofwords.repository.Repository
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DictionaryOfWordsInteractor @Inject constructor(
    private val repository: Repository<List<WordData>>
) : Interactor<WordsState> {

    override fun getWordsData(word: String, isRemoteSource: Boolean): Observable<WordsState> {
        return repository.getWordsData(word, isRemoteSource).map { WordsState.Success(it) }
    }

}