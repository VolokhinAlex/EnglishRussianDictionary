package com.volokhinaleksey.datasource.description

import com.volokhinaleksey.models.remote.MeaningDTO

class FakeRemoteDescriptionDataSource : DescriptionDataSource {

    private val meanings = mutableListOf<MeaningDTO>()

    override suspend fun getMeaningsData(meaningId: Long): List<MeaningDTO> {
        return meanings.filter { it.id == meaningId }
    }

}