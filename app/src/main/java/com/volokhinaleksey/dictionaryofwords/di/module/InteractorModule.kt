package com.volokhinaleksey.dictionaryofwords.di.module

import com.volokhinaleksey.dictionaryofwords.interactor.DictionaryOfWordsInteractor
import com.volokhinaleksey.dictionaryofwords.interactor.Interactor
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface InteractorModule {

    @Binds
    @Singleton
    fun provideDictionaryInteractor(dictionaryInteractor: DictionaryOfWordsInteractor): Interactor<WordsState>

}