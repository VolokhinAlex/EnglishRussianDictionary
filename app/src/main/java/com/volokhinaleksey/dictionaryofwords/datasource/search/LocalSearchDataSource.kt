package com.volokhinaleksey.dictionaryofwords.datasource.search

import com.volokhinaleksey.dictionaryofwords.states.WordsState

interface LocalSearchDataSource : SearchDataSource {

    suspend fun saveWordToDB(wordsState: WordsState)

}