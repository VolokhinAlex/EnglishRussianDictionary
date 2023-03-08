package com.volokhinaleksey.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * A data model for a local database.
 * This data model is completely dependent on WordEntity and its id column.
 * This data model is needed to store the search history
 *
 * @param wordId - The ID of the word that was added to history, as well as this primary key
 * @param word - The word that was being searched for
 *
 */

@Entity(
    tableName = "history_table",
    foreignKeys = [ForeignKey(
        entity = WordEntity::class,
        parentColumns = ["id"],
        childColumns = ["word_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = arrayOf("word"), unique = true)]
)
data class HistoryEntity(
    @PrimaryKey
    val word: String,
    @ColumnInfo(name = "word_id")
    val wordId: Long
)