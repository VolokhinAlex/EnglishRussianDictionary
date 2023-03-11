package com.volokhinaleksey.dictionaryofwords.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * A class with information about the translated word
 * @param translation - Translated word
 */

@Parcelize
data class TranslationDTO(
    @field: SerializedName("text") val translation: String?
) : Parcelable