package com.volokhinaleksey.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

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