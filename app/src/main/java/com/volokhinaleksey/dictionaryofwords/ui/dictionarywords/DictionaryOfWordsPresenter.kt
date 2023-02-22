package com.volokhinaleksey.dictionaryofwords.ui.dictionarywords

import com.volokhinaleksey.dictionaryofwords.presenter.Presenter
import com.volokhinaleksey.dictionaryofwords.schedulers.SchedulersProvider
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import com.volokhinaleksey.dictionaryofwords.ui.base.BaseView
import io.reactivex.rxjava3.disposables.CompositeDisposable

class DictionaryOfWordsPresenter(
    private val mainInteractor: MainInteractor,
    private val schedulers: SchedulersProvider
) : Presenter<WordsState, BaseView> {

    private val compositeDisposable = CompositeDisposable()

    private var currentView: BaseView? = null

    override fun viewAttach(view: BaseView) {
        if (currentView != view) {
            currentView = view
        }
    }

    override fun viewDestroy(view: BaseView) {
        compositeDisposable.clear()
        if (currentView == view) {
            currentView = null
        }
    }

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