package com.volokhinaleksey.dictionaryofwords.datasource

import io.reactivex.rxjava3.core.Observable
import com.volokhinaleksey.dictionaryofwords.model.WordData

interface DictionaryDataSource {
    fun getWordsData(word: String): Observable<List<WordData>>
}