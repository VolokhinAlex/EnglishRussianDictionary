package com.volokhinaleksey.models.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meaning(
    val id: Long = 0,
    val translation: Translation = Translation(translation = ""),
    val imageUrl: String = "",
    val wordId: Long = 0,
    val word: String = "",
    val transcription: String = "",
    val definition: Definition = Definition(wordDefinition = ""),
    val examples: List<ExampleWord> = emptyList(),
    val similarTranslation: List<SimilarTranslation> = emptyList()
): Parcelable