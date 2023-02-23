package com.volokhinaleksey.dictionaryofwords.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.volokhinaleksey.dictionaryofwords.di.annotation.ViewModelKey
import com.volokhinaleksey.dictionaryofwords.di.factory.ViewModelFactory
import com.volokhinaleksey.dictionaryofwords.viewmodel.DictionaryOfWordsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [InteractorModule::class])
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @ViewModelKey(keyValue = DictionaryOfWordsViewModel::class)
    @IntoMap
    internal abstract fun mainViewModel(dictionaryOfWordsViewModel: DictionaryOfWordsViewModel): ViewModel

}