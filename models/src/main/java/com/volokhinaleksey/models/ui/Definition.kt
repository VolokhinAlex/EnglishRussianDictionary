package com.volokhinaleksey.models.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data model for the UI layer of the application
 * This data model is needed to store the definition of a word
 *
 * @param wordDefinition - Definition of the word
 *
 */

@Parcelize
data class Definition(
    val wordDefinition: String = ""
) : Parcelable