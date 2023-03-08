package com.volokhinaleksey.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

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
    @ColumnInfo(name = "meaning_id")
    val meaningId: Long,
    @PrimaryKey
    @ColumnInfo(name = "example_text")
    val exampleText: String
)