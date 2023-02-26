package com.volokhinaleksey.dictionaryofwords.repository

import com.volokhinaleksey.dictionaryofwords.datasource.DictionaryDataSource
import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningsDataDTO

class MeaningsRepositoryImpl(
    private val remoteDataSource: DictionaryDataSource,
    private val localDataSource: DictionaryDataSource
) : MeaningsRepository {

    override suspend fun getMeaningsData(meaningId: Long, isRemoteSource: Boolean): List<MeaningsDataDTO> {
        return if (isRemoteSource) {
            remoteDataSource.getMeaningsData(meaningId = meaningId)
        } else {
            localDataSource.getMeaningsData(meaningId = meaningId)
        }
    }

}