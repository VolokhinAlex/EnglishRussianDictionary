package com.volokhinaleksey.dictionaryofwords.repository.meanings

import com.volokhinaleksey.dictionaryofwords.datasource.description.DescriptionDataSource
import com.volokhinaleksey.dictionaryofwords.datasource.description.LocalDescriptionDataSource
import com.volokhinaleksey.dictionaryofwords.model.remote.FavoriteWord
import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningDTO

/**
 * Implementation of an interface for storing values obtained from some data source
 */

class MeaningsRepositoryImpl(
    private val remoteDataSource: DescriptionDataSource,
    private val localDataSource: LocalDescriptionDataSource
) : MeaningsRepository {

    /**
     * Method for getting a list of word meanings by meaning ID from some data source
     * @param meaningId - ID of the word meaning
     * @param isRemoteSource - Boolean value for determining the data source
     */

    override suspend fun getMeaningsData(
        meaningId: Long,
        isRemoteSource: Boolean
    ): List<MeaningDTO> {
        return if (isRemoteSource) {
            val meaningsData = remoteDataSource.getMeaningsData(meaningId = meaningId)
            localDataSource.saveWordToDB(meaningDTO = meaningsData)
            meaningsData
        } else {
            localDataSource.getMeaningsData(meaningId = meaningId)
        }
    }

    override suspend fun saveFavoriteWord(word: FavoriteWord) {
        localDataSource.saveFavoriteWord(favoriteWord = word)
    }

    override suspend fun getFavoriteWordFlag(wordId: Long): FavoriteWord? {
        return localDataSource.getFavoriteWordFlag(wordId = wordId)
    }

}