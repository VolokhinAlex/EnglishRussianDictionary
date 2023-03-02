package com.volokhinaleksey.dictionaryofwords.interactor.history

interface HistoryInteractor<T : Any> {

    suspend fun getHistoryData(): T

}