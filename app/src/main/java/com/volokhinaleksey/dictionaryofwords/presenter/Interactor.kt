package com.volokhinaleksey.dictionaryofwords.presenter

import io.reactivex.rxjava3.core.Observable


interface Interactor<T : Any> {

    fun getWordsData(word: String, isRemoteSource: Boolean): Observable<T>

}