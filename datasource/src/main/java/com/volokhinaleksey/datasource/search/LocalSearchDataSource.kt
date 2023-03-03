package com.volokhinaleksey.datasource.search

import com.volokhinaleksey.models.states.WordsState

interface LocalSearchDataSource : SearchDataSource {

    suspend fun saveWordToDB(wordsState: WordsState)

}