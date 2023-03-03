package com.volokhinaleksey.interactors.history

interface HistoryInteractor<T : Any> {

    suspend fun getHistoryData(): T

}