package com.volokhinaleksey.dictionaryofwords.app

import android.app.Application
import com.volokhinaleksey.dictionaryofwords.di.dictionaryOfWordsScreen
import com.volokhinaleksey.dictionaryofwords.di.networkModule
import com.volokhinaleksey.dictionaryofwords.di.repositoryModule
import org.koin.core.context.startKoin

class DictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(repositoryModule, networkModule, dictionaryOfWordsScreen))
        }
    }


}