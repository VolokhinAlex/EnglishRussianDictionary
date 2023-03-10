package com.volokhinaleksey.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.volokhinaleksey.models.local.FavoriteEntity

private const val FAVORITE_WORD = 1

/**
 * Interface for working with the table of favorite words of the local database
 */

@Dao
interface FavoriteDao {

    /**
     * Method for getting a favorite word by word id
     * @param wordId - Word ID
     */

    @Query("SELECT * FROM favorite_table WHERE word_id = :wordId")
    suspend fun getFavoriteWord(wordId: Long): FavoriteEntity?

    /**
     * Method for getting all the selected words
     */

    @Query("SELECT * FROM favorite_table WHERE favorite = $FAVORITE_WORD")
    suspend fun getFavoriteWords(): List<FavoriteEntity>

    /**
     * Method for adding a favorite word to the database
     * @param entity - The entity to be added to the database
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FavoriteEntity)

    /**
     * A method for adding favorite words to a database
     * @param entity - The list of entities to be added to databases
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<FavoriteEntity>)

    /**
     * Method for updating a favorite word in a database
     * @param entity - The entity that needs to be updated in the database
     */

    @Update
    suspend fun update(entity: FavoriteEntity)

    /**
     * Method for deleting a favorite word from the database
     * @param entity - The entity to be deleted from the database
     */

    @Delete
    suspend fun delete(entity: FavoriteEntity)

}