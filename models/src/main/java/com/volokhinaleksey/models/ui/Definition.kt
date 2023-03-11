package com.volokhinaleksey.models.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Definition(
    val wordDefinition: String = ""
) : Parcelable