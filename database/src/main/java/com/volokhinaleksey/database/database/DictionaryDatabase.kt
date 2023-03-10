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

/**
 * Database Room object for combining DAO objects with entities
 */

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

    /**
     * WordDao for working with word entity
     */

    abstract fun wordDao(): WordDao

    /**
     * WordDao for working with History Entity
     */

    abstract fun historyDao(): HistoryDao

    /**
     * WordDao for working with Meaning Entity
     */

    abstract fun meaningDao(): MeaningDao

    /**
     * WordDao for working with Favorite Entity
     */

    abstract fun favoriteDao(): FavoriteDao

    /**
     * WordDao for working with ExampleEntity
     */

    abstract fun exampleDao(): ExampleDao

    /**
     * WordDao for working with SimilarTranslation Entity
     */

    abstract fun similarTranslationDao(): SimilarTranslationDao

}