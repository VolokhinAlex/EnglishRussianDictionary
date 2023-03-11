package com.volokhinaleksey.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.volokhinaleksey.models.local.SimilarTranslationEntity

/**
 * Interface for working with the table of favorite words of the local database
 */

@Dao
interface SimilarTranslationDao {

    /**
     * Method for getting a list of similar translations by meaning id
     * @param meaningId - Id of the word meaning
     */

    @Query("SELECT * FROM similar_translation_table WHERE meaning_id = :meaningId")
    suspend fun getSimilarTranslationById(meaningId: Long): List<SimilarTranslationEntity>

    /**
     * Method for adding a similar translation of a word to the database
     * @param entity - The entity to be added to the database
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: SimilarTranslationEntity)

    /**
     * A method for adding similar translations of a word to a database
     * @param entity - The list of entities to be added to databases
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: List<SimilarTranslationEntity>)

    /**
     * Method for updating a similar translation of a word in a database
     * @param entity - The entity that needs to be updated in the database
     */

    @Update
    suspend fun update(entity: SimilarTranslationEntity)

    /**
     * Method for deleting a similar translation of a word from the database
     * @param entity - The entity to be deleted from the database
     */

    @Delete
    suspend fun delete(entity: SimilarTranslationEntity)

}