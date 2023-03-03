package com.volokhinaleksey.repositories.favorite

import com.volokhinaleksey.datasource.favorite.FavoriteDataSource
import com.volokhinaleksey.models.remote.FavoriteWord

class FavoriteRepositoryImpl(
    private val favoriteDataSource: FavoriteDataSource
) : FavoriteRepository {
    override suspend fun getFavoritesData(): List<FavoriteWord> {
        return favoriteDataSource.getFavorites()
    }

    override suspend fun deleteFavoriteWord(word: FavoriteWord) {
        favoriteDataSource.deleteFavoriteWord(word = word)
    }

}