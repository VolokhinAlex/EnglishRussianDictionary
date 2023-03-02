package com.volokhinaleksey.dictionaryofwords.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.volokhinaleksey.dictionaryofwords.model.local.FavoriteEntity
import com.volokhinaleksey.dictionaryofwords.model.local.HistoryEntity
import com.volokhinaleksey.dictionaryofwords.model.local.MeaningEntity
import com.volokhinaleksey.dictionaryofwords.model.local.WordEntity
import com.volokhinaleksey.dictionaryofwords.room.dao.FavoriteDao
import com.volokhinaleksey.dictionaryofwords.room.dao.HistoryDao
import com.volokhinaleksey.dictionaryofwords.room.dao.MeaningDao
import com.volokhinaleksey.dictionaryofwords.room.dao.WordDao

@Database(
    entities = [WordEntity::class, HistoryEntity::class, MeaningEntity::class, FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DictionaryDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun historyDao(): HistoryDao

    abstract fun meaningDao(): MeaningDao
    abstract fun favoriteDao(): FavoriteDao

}