package com.volokhinaleksey.models.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * A data class for a similar translation of a word
 * @param partOfSpeechAbbreviation - Part of speech in a sentence
 * @param translation - Word translation
 */

@Parcelize
data class SimilarTranslationDTO(
    @field: SerializedName("partOfSpeechAbbreviation") val partOfSpeechAbbreviation: String?,
    @field: SerializedName("translation") val translation: TranslationDTO?
) : Parcelable