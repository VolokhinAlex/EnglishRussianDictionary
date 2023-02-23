package com.volokhinaleksey.dictionaryofwords.di.module

import com.volokhinaleksey.dictionaryofwords.model.WordData
import com.volokhinaleksey.dictionaryofwords.repository.DictionaryOfWordsRepository
import com.volokhinaleksey.dictionaryofwords.repository.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun dictionaryOfWordsRepository(impl: DictionaryOfWordsRepository): Repository<List<WordData>>

}