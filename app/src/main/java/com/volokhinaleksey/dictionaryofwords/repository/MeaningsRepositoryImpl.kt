package com.volokhinaleksey.dictionaryofwords.repository

import com.volokhinaleksey.dictionaryofwords.datasource.DictionaryDataSource
import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningDTO

/**
 * Implementation of an interface for storing values obtained from some data source
 */

class MeaningsRepositoryImpl(
    private val remoteDataSource: DictionaryDataSource,
    private val localDataSource: DictionaryDataSource
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
            remoteDataSource.getMeaningsData(meaningId = meaningId)
        } else {
            localDataSource.getMeaningsData(meaningId = meaningId)
        }
    }

}