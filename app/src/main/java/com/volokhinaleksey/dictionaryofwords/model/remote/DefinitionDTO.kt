package com.volokhinaleksey.dictionaryofwords.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Data class for word definition.
 * @param text - Word Definition
 */

@Parcelize
data class DefinitionDTO(
    @field: SerializedName("text") val text: String?
) : Parcelable