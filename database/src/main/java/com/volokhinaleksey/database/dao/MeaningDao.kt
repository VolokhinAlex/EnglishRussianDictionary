package com.volokhinaleksey.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.volokhinaleksey.models.local.MeaningEntity

/**
 * Interface for working with the table of favorite words of the local database
 */

@Dao
interface MeaningDao {

    /**
     * Method for getting a list of word meanings by word id
     */

    @Query("SELECT * FROM meaning_table WHERE word_id = :wordId")
    suspend fun getWordMeaningByWordId(wordId: Long): List<MeaningEntity>

    /**
     * Method for getting a list of the meaning of a word by the meaning id
     * @param meaningId - Id of the word meaning
     */

    @Query("SELECT * FROM meaning_table WHERE id = :meaningId")
    suspend fun getWordMeaningById(meaningId: Long): List<MeaningEntity>

    /**
     * Method for adding a meaning of the word to the database
     * @param entity - The entity to be added to the database
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: MeaningEntity)

    /**
     * A method for adding meanings of the word to a database
     * @param entity - The list of entities to be added to databases
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<MeaningEntity>)

    /**
     * Method for updating a meaning of the word in a database
     * @param entity - The entity that needs to be updated in the database
     */

    @Update
    suspend fun update(entity: MeaningEntity)

    /**
     * Method for deleting a meaning of the word from the database
     * @param entity - The entity to be deleted from the database
     */

    @Delete
    suspend fun delete(entity: MeaningEntity)

}