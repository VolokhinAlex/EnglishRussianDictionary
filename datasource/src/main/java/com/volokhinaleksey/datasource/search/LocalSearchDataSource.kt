package com.volokhinaleksey.datasource.search

import com.volokhinaleksey.models.remote.WordDTO

/**
 * Interface for getting data from some data source, for a dictionary
 */

interface LocalSearchDataSource : SearchDataSource {

    /**
     * A method for saving a word to a local database
     * @param word - The word to save to the database
     */

    suspend fun saveWordToDB(word: List<WordDTO>)

}