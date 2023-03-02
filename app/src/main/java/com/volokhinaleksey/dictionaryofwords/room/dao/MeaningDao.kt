package com.volokhinaleksey.dictionaryofwords.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.volokhinaleksey.dictionaryofwords.model.local.MeaningEntity

@Dao
interface MeaningDao {

    @Query("SELECT * FROM meaning_table")
    suspend fun all(): List<MeaningEntity>


    @Query("SELECT * FROM meaning_table WHERE word_id = :wordId")
    suspend fun getWordMeaningByWordId(wordId: Long): List<MeaningEntity>

    @Query("SELECT * FROM meaning_table WHERE id = :meaningId")
    suspend fun getWordMeaningById(meaningId: Long): List<MeaningEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: MeaningEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<MeaningEntity>)

    @Update
    suspend fun update(entity: MeaningEntity)

    @Delete
    suspend fun delete(entity: MeaningEntity)

}