package com.volokhinaleksey.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.volokhinaleksey.models.local.ExampleEntity

@Dao
interface ExampleDao {

    @Query("SELECT * FROM example_table WHERE meaning_id = :meaningId")
    suspend fun getExampleWordById(meaningId: Long): List<ExampleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ExampleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<ExampleEntity>)

    @Update
    suspend fun update(entity: ExampleEntity)

    @Delete
    suspend fun delete(entity: ExampleEntity)

}