package com.volokhinaleksey.dictionaryofwords.repository

import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningsDataDTO

interface MeaningsRepository {

    suspend fun getMeaningsData(meaningId: Long, isRemoteSource: Boolean): List<MeaningsDataDTO>

}