package com.volokhinaleksey.dictionaryofwords.repository

import com.volokhinaleksey.dictionaryofwords.model.WordData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


class DictionaryOfWordsRepository @Inject constructor(
    private val apiHolder: ApiHolder
) : Repository<List<@JvmSuppressWildcards WordData>> {

    override fun getWordsData(word: String, isRemoteSource: Boolean): Observable<List<WordData>> {
        return if (isRemoteSource) {
            apiHolder.apiService.getWordsBySearch(wordToSearch = word)
        } else {
            // Local Source
            apiHolder.apiService.getWordsBySearch(wordToSearch = word)
        }
    }


}