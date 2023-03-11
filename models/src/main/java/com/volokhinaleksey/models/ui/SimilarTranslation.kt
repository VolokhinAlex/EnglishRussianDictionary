package com.volokhinaleksey.models.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimilarTranslation(
    val partOfSpeechAbbreviation: String = "",
    val translation: Translation = Translation(translation = "")
): Parcelable