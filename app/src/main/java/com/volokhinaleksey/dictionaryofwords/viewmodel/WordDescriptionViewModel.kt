package com.volokhinaleksey.dictionaryofwords.viewmodel

import android.util.Log
import com.volokhinaleksey.dictionaryofwords.interactor.description.WordDescriptionInteractor
import com.volokhinaleksey.dictionaryofwords.states.MeaningsState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordDescriptionViewModel(
    private val interactor: WordDescriptionInteractor<MeaningsState>
) : BaseViewModel<MeaningsState>() {

    fun getMeanings(meaningId: Long, isOnline: Boolean) {
        currentMutableData.value = MeaningsState.Loading
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            Log.e("", throwable.message.toString())
            currentMutableData.postValue(MeaningsState.Error(throwable))
        }) {
            val responseRequest = interactor.getMeaningsData(meaningId = meaningId, isRemoteSource = isOnline)
            currentMutableData.postValue(responseRequest)
        }
    }

}