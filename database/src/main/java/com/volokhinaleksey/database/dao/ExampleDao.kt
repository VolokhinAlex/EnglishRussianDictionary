package com.volokhinaleksey.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.volokhinaleksey.models.local.ExampleEntity

/**
 * Interface for working with a table of examples of a local database
 */

@Dao
interface ExampleDao {

    /**
     * Method for getting a list of examples of using a word by meaning id
     * @param meaningId - Meaning ID
     */

    @Query("SELECT * FROM example_table WHERE meaning_id = :meaningId")
    suspend fun getExampleWordById(meaningId: Long): List<ExampleEntity>

    /**
     * Method for adding an example of the use of a word to the database
     * @param entity - The entity to be added to the database
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ExampleEntity)

    /**
     * A method for adding examples of the use of a word to a database
     * @param entity - The list of entities to be added to databases
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<ExampleEntity>)

    /**
     * Method for updating an example of using a word in a database
     * @param entity - The entity that needs to be updated in the database
     */

    @Update
    suspend fun update(entity: ExampleEntity)

    /**
     * Method for deleting an example of using a word from the database
     * @param entity - The entity to be deleted from the database
     */

    @Delete
    suspend fun delete(entity: ExampleEntity)

}