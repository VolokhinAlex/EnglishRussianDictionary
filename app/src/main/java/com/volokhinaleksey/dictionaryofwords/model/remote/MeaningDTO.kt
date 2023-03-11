package com.volokhinaleksey.dictionaryofwords.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 *  The meaning of the word that needed to be translated
 *  @param translation - Word translation
 *  @param imageUrl - The URL of the image for the word to be translated
 */

@Parcelize
data class MeaningDTO(
    @field: SerializedName("id") val id: Long?,
    @field: SerializedName("translation") val translation: TranslationDTO?,
    @field: SerializedName("imageUrl") val imageUrl: String?
) : Parcelable