package com.volokhinaleksey.models.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteWord(
    val wordId: Long,
    val word: String,
    val isFavorite: Boolean,
    val meanings: List<Meaning>
) : Parcelable