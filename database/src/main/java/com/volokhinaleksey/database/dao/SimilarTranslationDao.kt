package com.volokhinaleksey.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.volokhinaleksey.models.local.SimilarTranslationEntity

@Dao
interface SimilarTranslationDao {

    @Query("SELECT * FROM similar_translation_table WHERE meaning_id = :meaningId")
    suspend fun getSimilarTranslationById(meaningId: Long): List<SimilarTranslationEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: SimilarTranslationEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: List<SimilarTranslationEntity>)

    @Update
    suspend fun update(entity: SimilarTranslationEntity)

    @Delete
    suspend fun delete(entity: SimilarTranslationEntity)

}