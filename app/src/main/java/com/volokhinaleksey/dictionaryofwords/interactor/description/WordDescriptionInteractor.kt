package com.volokhinaleksey.dictionaryofwords.interactor.description

interface WordDescriptionInteractor<T : Any> {

    suspend fun getMeaningsData(meaningId: Long, isRemoteSource: Boolean): T

}