package com.volokhinaleksey.dictionaryofwords.app

import android.app.Application
import com.volokhinaleksey.dictionaryofwords.di.databaseModule
import com.volokhinaleksey.dictionaryofwords.di.dictionaryOfWordsScreen
import com.volokhinaleksey.dictionaryofwords.di.historyScreen
import com.volokhinaleksey.dictionaryofwords.di.networkModule
import com.volokhinaleksey.dictionaryofwords.di.repositoryModule
import com.volokhinaleksey.dictionaryofwords.di.wordDescriptionScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    databaseModule,
                    repositoryModule,
                    networkModule,
                    dictionaryOfWordsScreen,
                    wordDescriptionScreen,
                    historyScreen
                )
            )
        }
    }


}