package com.volokhinaleksey.repositories.favorite

import com.volokhinaleksey.models.remote.FavoriteWord

interface FavoriteRepository {

    suspend fun getFavoritesData(): List<FavoriteWord>

    suspend fun deleteFavoriteWord(word: FavoriteWord)

}