package com.volokhinaleksey.dictionaryofwords.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimilarTranslationDTO(
    @field: SerializedName("partOfSpeechAbbreviation") val abbreviation: String?,
    @field: SerializedName("translation") val translation: TranslationDTO?
) : Parcelable