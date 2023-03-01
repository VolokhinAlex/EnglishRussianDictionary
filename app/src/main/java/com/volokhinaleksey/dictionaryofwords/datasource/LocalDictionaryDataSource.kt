package com.volokhinaleksey.dictionaryofwords.datasource

import com.volokhinaleksey.dictionaryofwords.model.WordData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class LocalDictionaryDataSource @Inject constructor() : DictionaryDataSource {

    override fun getWordsData(word: String): Observable<List<WordData>> {
        return Observable.just(
            listOf(
                WordData(
                    text = "Something from local data Source",
                    meanings = null
                )
            )
        )
    }

}