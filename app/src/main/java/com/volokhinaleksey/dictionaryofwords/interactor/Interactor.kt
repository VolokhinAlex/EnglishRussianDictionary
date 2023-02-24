package com.volokhinaleksey.dictionaryofwords.interactor

interface Interactor<T : Any> {

    suspend fun getWordsData(word: String, isRemoteSource: Boolean): T

}