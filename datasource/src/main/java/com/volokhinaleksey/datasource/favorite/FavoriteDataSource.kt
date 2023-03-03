package com.volokhinaleksey.datasource.favorite

import com.volokhinaleksey.models.remote.FavoriteWord

interface FavoriteDataSource {

    suspend fun getFavorites(): List<FavoriteWord>

    suspend fun deleteFavoriteWord(word: FavoriteWord)
}