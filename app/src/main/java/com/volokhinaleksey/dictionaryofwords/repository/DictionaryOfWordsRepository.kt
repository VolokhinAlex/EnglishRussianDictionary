package com.volokhinaleksey.dictionaryofwords.repository

import com.volokhinaleksey.dictionaryofwords.datasource.DictionaryDataSource
import com.volokhinaleksey.dictionaryofwords.model.WordData


class DictionaryOfWordsRepository (
    private val remoteDataSource: DictionaryDataSource,
    private val localDataSource: DictionaryDataSource
) : Repository<List<@JvmSuppressWildcards WordData>> {

    override suspend fun getWordsData(word: String, isRemoteSource: Boolean): List<WordData> {
        return if (isRemoteSource) {
            remoteDataSource.getWordsData(word = word)
        } else {
            localDataSource.getWordsData(word = word)
        }
    }


}