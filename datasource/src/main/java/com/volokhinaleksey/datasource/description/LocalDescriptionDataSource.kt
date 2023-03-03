package com.volokhinaleksey.datasource.description

interface LocalDescriptionDataSource : DescriptionDataSource {

    suspend fun saveWordToDB(meaningDTO: List<com.volokhinaleksey.models.remote.MeaningDTO>)

    suspend fun saveFavoriteWord(favoriteWord: com.volokhinaleksey.models.remote.FavoriteWord)

    suspend fun getFavoriteWordFlag(wordId: Long): com.volokhinaleksey.models.remote.FavoriteWord?
}