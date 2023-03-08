package com.volokhinaleksey.models.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data model for the UI layer of the application
 * This data model is needed to store the meaning of the word
 *
 * @param id - Word ID
 * @param translation - Word translation
 * @param imageUrl - Link to the image
 * @param wordId - ID of the word for which meanings are stored
 * @param word - The word for which meanings are stored
 * @param transcription - Transcription of the word
 * @param definition - Definition of the word
 * @param examples - Examples of the use of the word in the text
 * @param similarTranslation - Similar translations of the word
 *
 */

@Parcelize
data class Meaning(
    val id: Long = 0,
    val translation: Translation = Translation(translation = ""),
    val imageUrl: String = "",
    val wordId: Long = 0,
    val word: String = "",
    val transcription: String = "",
    val definition: Definition = Definition(wordDefinition = ""),
    val examples: List<ExampleWord> = emptyList(),
    val similarTranslation: List<SimilarTranslation> = emptyList()
) : Parcelable