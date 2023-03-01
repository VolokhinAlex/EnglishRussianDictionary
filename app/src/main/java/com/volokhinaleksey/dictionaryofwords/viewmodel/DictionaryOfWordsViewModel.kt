package com.volokhinaleksey.dictionaryofwords.viewmodel

import com.volokhinaleksey.dictionaryofwords.interactor.Interactor
import com.volokhinaleksey.dictionaryofwords.schedulers.SchedulersProvider
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import javax.inject.Inject

class DictionaryOfWordsViewModel @Inject constructor(
    private val schedulers: SchedulersProvider,
    private val interactor: Interactor<WordsState>
) : BaseViewModel<WordsState>() {

    fun getWordMeanings(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getWordsData(word = word, isRemoteSource = isOnline)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .doOnSubscribe {
                    currentMutableData.value = WordsState.Loading
                }
                .subscribe({
                    currentMutableData.value = it
                }, {
                    currentMutableData.value = WordsState.Error(it)
                })
        )
    }

}