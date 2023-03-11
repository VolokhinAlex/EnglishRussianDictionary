package com.volokhinaleksey.dictionaryofwords.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Data class for word usage examples
 * @param text - Example of using a word in the text
 */

@Parcelize
data class ExampleDTO(
    @field: SerializedName("text") val text: String?
) : Parcelable