package com.volokhinaleksey.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.volokhinaleksey.models.local.WordEntity

@Dao
interface WordDao {

    @Query("SELECT * FROM word_table WHERE LOWER(word) LIKE '%' || :word || '%' ")
    suspend fun getDataByWord(word: String): List<WordEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wordEntity: WordEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wordEntity: List<WordEntity>)

    @Update
    suspend fun update(entity: WordEntity)

    @Delete
    suspend fun delete(wordEntity: WordEntity)

}