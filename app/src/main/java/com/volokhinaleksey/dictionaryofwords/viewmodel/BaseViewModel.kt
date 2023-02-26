package com.volokhinaleksey.dictionaryofwords.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

/**
 * The base class for all ViewModels, which includes the initial configuration to work with different methods.
 */

abstract class BaseViewModel<T : Any> : ViewModel() {

    protected val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    protected val currentMutableData: MutableLiveData<T> = MutableLiveData()

    val currentData: LiveData<T> get() = currentMutableData

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}