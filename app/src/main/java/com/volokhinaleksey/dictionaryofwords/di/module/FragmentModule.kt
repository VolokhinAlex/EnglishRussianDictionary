package com.volokhinaleksey.dictionaryofwords.di.module

import com.volokhinaleksey.dictionaryofwords.ui.dictionarywords.DictionaryOfWordsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeDictionaryOfWordsFragment(): DictionaryOfWordsFragment

}