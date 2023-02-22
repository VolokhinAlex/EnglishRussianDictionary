package com.volokhinaleksey.dictionaryofwords.ui.base

import com.volokhinaleksey.dictionaryofwords.states.WordsState

interface BaseView {

    fun renderData(wordsState: WordsState)

}