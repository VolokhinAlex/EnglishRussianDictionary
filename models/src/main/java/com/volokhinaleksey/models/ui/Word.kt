package com.volokhinaleksey.models.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data model for the UI layer of the application
 * This data model is needed to store a word
 *
 * @param id - Unique identifier of the word
 * @param word - The word that needs to be translated
 * @param meanings - The list of meanings of a word.
 *
 */

@Parcelize
data class Word(
    val id: Long = 0,
    val word: String = "",
    val meanings: List<Meaning> = emptyList()
): Parcelable