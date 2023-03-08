package com.volokhinaleksey.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * A data model for a local database.
 * This data model is completely dependent on WordEntity and its id column.
 * This data model is needed to store selected words
 *
 * @param wordId - The ID of the word that was added to favorites, as well as this primary key
 * @param isFavorite - Flag marking whether a word is selected or not.
 * @param word - The favorite word
 *
 */

@Entity(
    tableName = "favorite_table", foreignKeys = [ForeignKey(
        entity = WordEntity::class,
        parentColumns = ["id"],
        childColumns = ["word_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo("word_id")
    val wordId: Long,
    @ColumnInfo("favorite")
    val isFavorite: Boolean,
    val word: String
)