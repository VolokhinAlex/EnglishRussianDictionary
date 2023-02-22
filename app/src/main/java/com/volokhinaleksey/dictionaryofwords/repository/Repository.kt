package com.volokhinaleksey.dictionaryofwords.repository

import io.reactivex.rxjava3.core.Observable


interface Repository<T : Any> {

    fun getWordsData(word: String): Observable<T>

}