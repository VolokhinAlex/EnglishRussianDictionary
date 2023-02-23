package com.volokhinaleksey.dictionaryofwords.di.module

import com.volokhinaleksey.dictionaryofwords.schedulers.SchedulersProvider
import com.volokhinaleksey.dictionaryofwords.schedulers.SchedulersProviderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface SchedulersProviderModule {

    @Binds
    @Singleton
    fun schedulers(schedulersImpl: SchedulersProviderImpl): SchedulersProvider

}