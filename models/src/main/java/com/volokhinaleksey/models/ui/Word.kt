package com.volokhinaleksey.models.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Word(
    val id: Long = 0,
    val word: String = "",
    val meanings: List<Meaning> = emptyList()
): Parcelable