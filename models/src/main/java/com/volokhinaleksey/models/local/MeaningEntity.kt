package com.volokhinaleksey.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * A data model for a local database.
 * This data model is completely dependent on WordEntity and its id column.
 * This data model is needed to store the meaning of the translated word
 *
 * @param id -  The identifier of the meaning of the word, as well as this primary key
 * @param translation - Word translation
 * @param imageUrl - Link to the image
 * @param wordId - ID of the word for which meanings are stored
 * @param word - The word for which meanings are stored
 * @param transcription - Transcription of the word
 * @param definition - Definition of the word
 *
 */

@Entity(
    tableName = "meaning_table",
    foreignKeys = [ForeignKey(
        entity = WordEntity::class,
        parentColumns = ["id"],
        childColumns = ["word_id"],
        onDelete = ForeignKey.CASCADE
    )],
)
data class MeaningEntity(
    @PrimaryKey
    val id: Long,
    val translation: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "word_id")
    val wordId: Long,
    val word: String,
    val transcription: String,
    val definition: String
)