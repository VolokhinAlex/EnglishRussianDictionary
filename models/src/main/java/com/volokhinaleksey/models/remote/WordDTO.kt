package com.volokhinaleksey.models.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Data model for a dictionary of words
 * @param id - Unique identifier of the word
 * @param text - Words that needed to be translated
 * @param meanings - The meanings of the word that needed to be translated
 */

@Parcelize
data class WordDTO(
    @field: SerializedName("id") val id: Long?,
    @field: SerializedName("text") val text: String?,
    @field: SerializedName("meanings") val meanings: List<MeaningDTO>?
) : Parcelable
