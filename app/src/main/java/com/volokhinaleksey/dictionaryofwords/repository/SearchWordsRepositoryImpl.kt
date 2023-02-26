package com.volokhinaleksey.dictionaryofwords.repository

import com.volokhinaleksey.dictionaryofwords.datasource.DictionaryDataSource
import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO


class SearchWordsRepositoryImpl(
    private val remoteDataSource: DictionaryDataSource,
    private val localDataSource: DictionaryDataSource
) : SearchWordsRepository {

    override suspend fun getWordsData(word: String, isRemoteSource: Boolean): List<WordDTO> {
        return if (isRemoteSource) {
            remoteDataSource.getWordsData(word = word)
        } else {
            localDataSource.getWordsData(word = word)
        }
    }

}