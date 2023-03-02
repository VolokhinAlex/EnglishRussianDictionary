package com.volokhinaleksey.dictionaryofwords.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningDTO

@Entity(tableName = "word_table")
data class WordEntity(
    @PrimaryKey
    val id: Long,
    val word: String
)