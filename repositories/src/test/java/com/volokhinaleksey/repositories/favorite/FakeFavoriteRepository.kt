package com.volokhinaleksey.repositories.favorite

import com.volokhinaleksey.models.ui.FavoriteWord

class FakeFavoriteRepository : FavoriteRepository {

    private val favorites: MutableList<FavoriteWord> = mutableListOf()

    override suspend fun getFavoritesData(): List<FavoriteWord> {
        return favorites
    }

    override suspend fun deleteFavoriteWord(word: FavoriteWord) {
        favorites.remove(word)
    }
}