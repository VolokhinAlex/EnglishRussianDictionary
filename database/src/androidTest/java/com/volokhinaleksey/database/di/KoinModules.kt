package com.volokhinaleksey.database.di

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.volokhinaleksey.database.database.DictionaryDatabase
import org.koin.dsl.module

val databaseTestingModule = module {
    factory {
        Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DictionaryDatabase::class.java
        ).allowMainThreadQueries().build()
    }
}