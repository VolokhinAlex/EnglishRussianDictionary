package com.volokhinaleksey.dictionaryofwords.ui.dictionarywords

import com.volokhinaleksey.dictionaryofwords.ui.base.BasePresenter
import com.volokhinaleksey.dictionaryofwords.schedulers.SchedulersProvider
import com.volokhinaleksey.dictionaryofwords.states.WordsState

class DictionaryOfWordsPresenter(
    private val mainInteractor: DictionaryOfWordsInteractor,
    private val schedulers: SchedulersProvider
) : BasePresenter() {

    override fun getWordsData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            mainInteractor.getWordsData(word = word, isOnline)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .doOnSubscribe {
                    currentView?.renderData(WordsState.Loading)
                }
                .subscribe({
                    currentView?.renderData(it)
                }, {
                    currentView?.renderData(WordsState.Error(it))
                })
        )
    }

}