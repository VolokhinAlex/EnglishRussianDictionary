package com.volokhinaleksey.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * A data model for a local database.
 * This data model is completely dependent on MeaningEntity and its id column.
 * This data model is needed to store an example of the use of the word in the text
 *
 * @param exampleText - Example of using a word in the text
 * @param meaningId - ID of the word meaning
 *
 */

@Entity(
    tableName = "example_table",
    foreignKeys = [ForeignKey(
        entity = MeaningEntity::class,
        parentColumns = ["id"],
        childColumns = ["meaning_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = arrayOf("example_text"), unique = true)]
)
data class ExampleEntity(
    @PrimaryKey
    @ColumnInfo(name = "example_text")
    val exampleText: String,
    @ColumnInfo(name = "meaning_id")
    val meaningId: Long
)