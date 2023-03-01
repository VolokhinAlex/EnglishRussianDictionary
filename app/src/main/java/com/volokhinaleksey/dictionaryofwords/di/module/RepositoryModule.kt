package com.volokhinaleksey.dictionaryofwords.di.module

import com.volokhinaleksey.dictionaryofwords.datasource.DictionaryDataSource
import com.volokhinaleksey.dictionaryofwords.datasource.LocalDictionaryDataSource
import com.volokhinaleksey.dictionaryofwords.datasource.RemoteDictionaryDataSource
import com.volokhinaleksey.dictionaryofwords.di.LOCAL_SOURCE
import com.volokhinaleksey.dictionaryofwords.di.REMOTE_SOURCE
import com.volokhinaleksey.dictionaryofwords.model.WordData
import com.volokhinaleksey.dictionaryofwords.repository.DictionaryOfWordsRepository
import com.volokhinaleksey.dictionaryofwords.repository.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Named
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun dictionaryOfWordsRepository(impl: DictionaryOfWordsRepository): Repository<List<WordData>>

    @Binds
    @Singleton
    @Named(REMOTE_SOURCE)
    fun remoteDataSource(remoteDataSourceImpl: RemoteDictionaryDataSource): DictionaryDataSource

    @Binds
    @Singleton
    @Named(LOCAL_SOURCE)
    fun localDataSource(localDataSourceImpl: LocalDictionaryDataSource): DictionaryDataSource

}