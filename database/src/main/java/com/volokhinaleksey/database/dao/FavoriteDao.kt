package com.volokhinaleksey.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.volokhinaleksey.models.local.FavoriteEntity

private const val FAVORITE_WORD = 1

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite_table WHERE word_id = :wordId")
    suspend fun getFavoriteWord(wordId: Long): FavoriteEntity?

    @Query("SELECT * FROM favorite_table WHERE favorite = $FAVORITE_WORD")
    suspend fun getFavoriteWords(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FavoriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<FavoriteEntity>)

    @Update
    suspend fun update(entity: FavoriteEntity)

    @Delete
    suspend fun delete(entity: FavoriteEntity)

}