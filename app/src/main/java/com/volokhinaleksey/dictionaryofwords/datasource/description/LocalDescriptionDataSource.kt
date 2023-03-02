package com.volokhinaleksey.dictionaryofwords.datasource.description

import com.volokhinaleksey.dictionaryofwords.model.remote.MeaningDTO

interface LocalDescriptionDataSource : DescriptionDataSource {

    suspend fun saveWordToDB(meaningDTO: List<MeaningDTO>)

}