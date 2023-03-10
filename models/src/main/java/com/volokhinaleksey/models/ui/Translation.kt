package com.volokhinaleksey.models.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data model for the UI layer of the application
 * This data model is needed to store a translation of a word
 *
 * @param translation - Word translation
 *
 */

@Parcelize
data class Translation(
    val translation: String = ""
): Parcelable