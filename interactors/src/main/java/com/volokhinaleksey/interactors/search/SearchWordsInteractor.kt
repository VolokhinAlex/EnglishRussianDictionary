package com.volokhinaleksey.interactors.search

/**
 * An interface for describing the logic of an application that does not depend on the
 * framework and platform in any way
 */

interface SearchWordsInteractor<T : Any> {

    /**
     * A method for getting the words by word from some source
     * @param word - The word for which you need to get words from the source
     * @param isRemoteSource - Boolean value for determining the data source
     */

    suspend fun getWordsData(word: String, isRemoteSource: Boolean): T

}