package com.volokhinaleksey.dictionaryofwords.interactor.search

interface SearchWordsInteractor<T : Any> {

    suspend fun getWordsData(word: String, isRemoteSource: Boolean): T

}