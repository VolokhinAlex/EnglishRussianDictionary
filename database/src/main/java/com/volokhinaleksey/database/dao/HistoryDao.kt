package com.volokhinaleksey.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.volokhinaleksey.models.local.HistoryEntity

/**
 * Interface for working with the table of favorite words of the local database
 */

@Dao
interface HistoryDao {

    /**
     * Method for getting all the history of words
     */

    @Query("SELECT * FROM history_table")
    suspend fun all(): List<HistoryEntity>

    /**
     * Method for adding the search history to the database
     * @param entity - The entity to be added to the database
     */


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: HistoryEntity)

    /**
     * A method for adding search histories to a database
     * @param entity - The list of entities to be added to databases
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: List<HistoryEntity>)

    /**
     * Method for deleting the search history from the database
     * @param entity - The entity to be deleted from the database
     */

    @Delete
    suspend fun delete(entity: HistoryEntity)
}