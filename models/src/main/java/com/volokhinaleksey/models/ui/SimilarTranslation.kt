package com.volokhinaleksey.models.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data model for the UI layer of the application
 * This data model is needed to store a similar translation of a word
 *
 * @param partOfSpeechAbbreviation - Part of speech words in abbreviated format
 * @param translation - Word translation
 *
 */

@Parcelize
data class SimilarTranslation(
    val partOfSpeechAbbreviation: String = "",
    val translation: Translation = Translation(translation = "")
): Parcelable