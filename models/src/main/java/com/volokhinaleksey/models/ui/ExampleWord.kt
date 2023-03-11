package com.volokhinaleksey.models.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data model for the UI layer of the application
 * This data model is necessary to store an example of the use of a word in the text
 *
 * @param exampleWord - Example of using a word in the text
 *
 */

@Parcelize
data class ExampleWord(
    val exampleWord: String = ""
): Parcelable