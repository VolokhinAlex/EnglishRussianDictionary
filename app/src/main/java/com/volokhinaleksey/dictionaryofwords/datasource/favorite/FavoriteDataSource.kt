package com.volokhinaleksey.dictionaryofwords.datasource.favorite

import com.volokhinaleksey.dictionaryofwords.model.remote.FavoriteWord

interface FavoriteDataSource {

    suspend fun getFavorites(): List<FavoriteWord>

}