package com.volokhinaleksey.dictionaryofwords.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 *  The meaning of the word that needed to be translated
 *  @param id - ID of the word meaning
 *  @param translation - Word translation
 *  @param imageUrl - The URL of the image for the word to be translated
 *  @param wordId - ID of the word for which meanings were found
 *  @param text - The word for which meanings were needed
 *  @param transcription - Transcription of the word
 *  @param definition - Word Definition
 *  @param examples - Example of using a word in the text
 *  @param similarTranslation - List of similar translations of the word
 */

@Parcelize
data class MeaningDTO(
    @field: SerializedName("id") val id: Long?,
    @field: SerializedName("translation") val translation: TranslationDTO?,
    @field: SerializedName("imageUrl") val imageUrl: String?,
    @field: SerializedName("wordId") val wordId: Long?,
    @field: SerializedName("text") val text: String?,
    @field: SerializedName("transcription") val transcription: String?,
    @field: SerializedName("definition") val definition: DefinitionDTO?,
    @field: SerializedName("examples") val examples: List<ExampleDTO>?,
    @field: SerializedName("meaningsWithSimilarTranslation") val similarTranslation: List<SimilarTranslationDTO>?,
) : Parcelable