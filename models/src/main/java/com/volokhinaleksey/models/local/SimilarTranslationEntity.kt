package com.volokhinaleksey.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * A data model for a local database.
 * This data model is completely dependent on MeaningEntity and its id column.
 * This data model is needed to store similar translations of the word
 *
 * @param translation - The translation of the word, as well as the primary key in the database.
 * @param meaningId - ID of the word meaning
 * @param partOfSpeechAbbreviation - Part of speech words in abbreviated format
 *
 */

@Entity(
    tableName = "similar_translation_table",
    foreignKeys = [ForeignKey(
        entity = MeaningEntity::class,
        parentColumns = ["id"],
        childColumns = ["meaning_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = arrayOf("translation"), unique = true)]
)
data class SimilarTranslationEntity(
    @PrimaryKey
    val translation: String,
    @ColumnInfo(name = "meaning_id")
    val meaningId: Long,
    @ColumnInfo(name = "part_of_speech_abbreviation")
    val partOfSpeechAbbreviation: String,
)