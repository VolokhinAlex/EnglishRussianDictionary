package com.volokhinaleksey.models.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data model for the UI layer of the application
 * This data model is needed to store the favorite word
 *
 * @param wordId - Word ID
 * @param word - The favorite word
 * @param isFavorite - A flag that allows you to understand when a word is a favorite and when it is not
 * @param meanings - List of word meanings
 *
 */

@Parcelize
data class FavoriteWord(
    val wordId: Long,
    val word: String,
    val isFavorite: Boolean,
    val meanings: List<Meaning>
) : Parcelable