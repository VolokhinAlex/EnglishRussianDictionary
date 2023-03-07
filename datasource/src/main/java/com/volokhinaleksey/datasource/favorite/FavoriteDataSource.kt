package com.volokhinaleksey.datasource.favorite

import com.volokhinaleksey.models.ui.FavoriteWord

interface FavoriteDataSource {

    suspend fun getFavorites(): List<FavoriteWord>

    suspend fun deleteFavoriteWord(word: FavoriteWord)
}