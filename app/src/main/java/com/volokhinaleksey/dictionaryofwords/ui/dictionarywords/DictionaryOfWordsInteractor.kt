package com.volokhinaleksey.dictionaryofwords.ui.dictionarywords

import com.volokhinaleksey.dictionaryofwords.model.WordData
import com.volokhinaleksey.dictionaryofwords.presenter.Interactor
import com.volokhinaleksey.dictionaryofwords.repository.Repository
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import io.reactivex.rxjava3.core.Observable

class DictionaryOfWordsInteractor(
    private val remoteRepository: Repository<List<WordData>>,
    private val localRepository: Repository<List<WordData>>
) : Interactor<WordsState> {

    override fun getWordsData(word: String, isRemoteSource: Boolean): Observable<WordsState> {
        return if (isRemoteSource) {
            remoteRepository.getWordsData(word).map { WordsState.Success(it) }
        } else {
            localRepository.getWordsData(word).map { WordsState.Success(it) }
        }
    }

}