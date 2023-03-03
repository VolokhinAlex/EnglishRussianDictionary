package com.volokhinaleksey.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.volokhinaleksey.models.remote.MeaningDTO

@Entity(tableName = "word_table")
data class WordEntity(
    @PrimaryKey
    val id: Long,
    val word: String
)