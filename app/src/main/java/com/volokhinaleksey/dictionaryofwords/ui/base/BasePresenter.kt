package com.volokhinaleksey.dictionaryofwords.ui.base

import com.volokhinaleksey.dictionaryofwords.presenter.Presenter
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BasePresenter : Presenter<WordsState, BaseView> {

    protected val compositeDisposable = CompositeDisposable()
    protected var currentView: BaseView? = null

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

    abstract override fun getWordsData(word: String, isOnline: Boolean)
}