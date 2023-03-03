package com.volokhinaleksey.dictionaryofwords.repository


interface Repository<T : Any> {

    suspend fun getWordsData(word: String, isRemoteSource: Boolean): T

}