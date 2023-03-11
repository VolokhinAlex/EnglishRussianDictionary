package com.volokhinaleksey.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.volokhinaleksey.database.dao.ExampleDao
import com.volokhinaleksey.database.dao.FavoriteDao
import com.volokhinaleksey.database.dao.HistoryDao
import com.volokhinaleksey.database.dao.MeaningDao
import com.volokhinaleksey.database.dao.SimilarTranslationDao
import com.volokhinaleksey.database.dao.WordDao
import com.volokhinaleksey.models.local.ExampleEntity
import com.volokhinaleksey.models.local.FavoriteEntity
import com.volokhinaleksey.models.local.HistoryEntity
import com.volokhinaleksey.models.local.MeaningEntity
import com.volokhinaleksey.models.local.SimilarTranslationEntity
import com.volokhinaleksey.models.local.WordEntity

@Database(
    entities = [
        WordEntity::class,
        HistoryEntity::class,
        MeaningEntity::class,
        FavoriteEntity::class,
        ExampleEntity::class,
        SimilarTranslationEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DictionaryDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun historyDao(): HistoryDao
    abstract fun meaningDao(): MeaningDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun exampleDao(): ExampleDao
    abstract fun similarTranslationDao(): SimilarTranslationDao

}