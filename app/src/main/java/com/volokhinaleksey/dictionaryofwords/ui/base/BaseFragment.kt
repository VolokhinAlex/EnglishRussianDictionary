package com.volokhinaleksey.dictionaryofwords.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.volokhinaleksey.dictionaryofwords.presenter.Presenter
import com.volokhinaleksey.dictionaryofwords.states.WordsState

abstract class BaseFragment : Fragment(), BaseView {

    protected var presenter: Presenter<WordsState, BaseView>? = null

    protected abstract fun createPresenter(): Presenter<WordsState, BaseView>

    abstract override fun renderData(wordsState: WordsState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter?.viewAttach(this)
    }

    override fun onStop() {
        super.onStop()
        presenter?.viewDestroy(this)
    }

}