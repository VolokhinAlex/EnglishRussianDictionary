package com.volokhinaleksey.interactors.history

/**
 * An interface for describing the logic of an application that does not depend on the
 * framework and platform in any way
 */

interface HistoryInteractor<T : Any> {

    /**
     * A method for getting search history from some data source
     */

    suspend fun getHistoryData(): T

}