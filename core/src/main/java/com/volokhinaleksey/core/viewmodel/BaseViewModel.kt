package com.volokhinaleksey.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    val currentData: LiveData<T> = currentMutableData

    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     */

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}