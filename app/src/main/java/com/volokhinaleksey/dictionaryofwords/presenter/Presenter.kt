package com.volokhinaleksey.dictionaryofwords.presenter

import com.volokhinaleksey.dictionaryofwords.states.WordsState
import com.volokhinaleksey.dictionaryofwords.ui.base.BaseView

interface Presenter<T : WordsState, BV : BaseView> {

    fun viewAttach(view: BV)
    fun viewDestroy(view: BV)
    fun getWordsData(word: String, isOnline: Boolean)


}