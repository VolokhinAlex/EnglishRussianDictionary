package com.volokhinaleksey.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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