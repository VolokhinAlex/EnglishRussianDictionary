package com.volokhinaleksey.dictionaryofwords.repository

import com.volokhinaleksey.dictionaryofwords.datasource.DictionaryDataSource
import com.volokhinaleksey.dictionaryofwords.di.LOCAL_SOURCE
import com.volokhinaleksey.dictionaryofwords.di.REMOTE_SOURCE
import com.volokhinaleksey.dictionaryofwords.model.WordData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Named


class DictionaryOfWordsRepository @Inject constructor(
    @Named(REMOTE_SOURCE) private val remoteDataSource: DictionaryDataSource,
    @Named(LOCAL_SOURCE) private val localDataSource: DictionaryDataSource
) : Repository<List<@JvmSuppressWildcards WordData>> {

    override fun getWordsData(word: String, isRemoteSource: Boolean): Observable<List<WordData>> {
        return if (isRemoteSource) {
            remoteDataSource.getWordsData(word = word)
        } else {
            localDataSource.getWordsData(word = word)
        }
    }


}