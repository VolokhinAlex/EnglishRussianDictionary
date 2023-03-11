package com.volokhinaleksey.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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