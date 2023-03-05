package com.volokhinaleksey.models.remote

data class FavoriteWord(
    val wordId: Long,
    val word: String,
    val isFavorite: Boolean,
    val meanings: List<MeaningDTO>
)