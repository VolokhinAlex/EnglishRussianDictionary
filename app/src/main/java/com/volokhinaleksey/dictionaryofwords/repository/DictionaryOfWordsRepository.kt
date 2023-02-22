package com.volokhinaleksey.dictionaryofwords.repository

import com.volokhinaleksey.dictionaryofwords.model.WordData
import io.reactivex.rxjava3.core.Observable


class DictionaryOfWordsRepository(
    private val apiHolder: ApiHolder
) : Repository<List<WordData>> {

    override fun getWordsData(word: String): Observable<List<WordData>> =
        apiHolder.apiService.getWordsBySearch(wordToSearch = word)


}