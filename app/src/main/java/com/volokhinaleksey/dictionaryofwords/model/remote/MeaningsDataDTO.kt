package com.volokhinaleksey.dictionaryofwords.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MeaningsDataDTO(
    @field: SerializedName("id") val id: String?,
    @field: SerializedName("wordId") val wordId: Long?,
    @field: SerializedName("text") val text: String?,
    @field: SerializedName("transcription") val transcription: String?,
    @field: SerializedName("translation") val translation: TranslationDTO?,
    @field: SerializedName("definition") val definition: DefinitionDTO?,
    @field: SerializedName("examples") val examples: List<ExampleDTO>?,
    @field: SerializedName("meaningsWithSimilarTranslation") val similarTranslation: List<SimilarTranslationDTO>?,
) : Parcelable
