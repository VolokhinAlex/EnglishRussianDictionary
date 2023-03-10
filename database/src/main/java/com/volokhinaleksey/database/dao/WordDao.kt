package com.volokhinaleksey.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.volokhinaleksey.models.local.WordEntity

/**
 * Interface for working with the table of favorite words of the local database
 */

@Dao
interface WordDao {

    /**
     * Method for getting a list of words by word
     */

    @Query("SELECT * FROM word_table WHERE LOWER(word) LIKE '%' || :word || '%' ")
    suspend fun getDataByWord(word: String): List<WordEntity>

    /**
     * Method for adding a word to the database
     * @param wordEntity - The entity to be added to the database
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wordEntity: WordEntity)

    /**
     * A method for adding words to a database
     * @param wordEntity - The list of entities to be added to databases
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wordEntity: List<WordEntity>)

    /**
     * Method for updating a word in a database
     * @param entity - The entity that needs to be updated in the database
     */

    @Update
    suspend fun update(entity: WordEntity)

    /**
     * Method for deleting a word from the database
     * @param wordEntity - The entity to be deleted from the database
     */

    @Delete
    suspend fun delete(wordEntity: WordEntity)

}