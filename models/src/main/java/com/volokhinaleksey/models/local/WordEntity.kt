package com.volokhinaleksey.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A data model for a local database.
 * This data model is needed to store words
 *
 * @param id - Unique identifier of the word
 * @param word - The word that needs to be translated
 *
 */

@Entity(tableName = "word_table")
data class WordEntity(
    @PrimaryKey
    val id: Long,
    val word: String
)