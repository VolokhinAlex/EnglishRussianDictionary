package com.volokhinaleksey.dictionaryofwords.repository

import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO


interface SearchWordsRepository {

    suspend fun getWordsData(word: String, isRemoteSource: Boolean): List<WordDTO>

}