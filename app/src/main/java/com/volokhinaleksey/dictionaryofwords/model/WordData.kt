package com.volokhinaleksey.dictionaryofwords.model

import com.google.gson.annotations.SerializedName

/**
 * Data model for a dictionary of words
 * @param text - Words that needed to be translated
 * @param meanings - The meanings of the word that needed to be translated
 */

data class WordData(
    @field: SerializedName("text") val text: String?,
    @field: SerializedName("meanings") val meanings: List<Meaning>?
)

/**
 *  The meaning of the word that needed to be translated
 *  @param translation - Word translation
 *  @param imageUrl - The URL of the image for the word to be translated
 */

data class Meaning(
    @field: SerializedName("translation") val translation: Translation?,
    @field: SerializedName("imageUrl") val imageUrl: String?
)

/**
 * A class with information about the translated word
 * @param translation - Translated word
 */

data class Translation(
    @field: SerializedName("text") val translation: String?
)