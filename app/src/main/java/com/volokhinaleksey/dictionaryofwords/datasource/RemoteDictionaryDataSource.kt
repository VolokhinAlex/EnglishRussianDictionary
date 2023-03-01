package com.volokhinaleksey.dictionaryofwords.datasource

import com.volokhinaleksey.dictionaryofwords.model.WordData
import com.volokhinaleksey.dictionaryofwords.repository.ApiHolder
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RemoteDictionaryDataSource @Inject constructor(
    private val apiHolder: ApiHolder
) : DictionaryDataSource {

    override fun getWordsData(word: String): Observable<List<WordData>> {
        return apiHolder.apiService.getWordsBySearch(wordToSearch = word)
    }

}