package com.volokhinaleksey.repositories.favorite

import com.volokhinaleksey.models.ui.FavoriteWord

interface FavoriteRepository {

    suspend fun getFavoritesData(): List<FavoriteWord>

    suspend fun deleteFavoriteWord(word: FavoriteWord)

}