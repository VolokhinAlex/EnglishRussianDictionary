package com.volokhinaleksey.core.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.volokhinaleksey.core.viewmodel.BaseViewModel
import com.volokhinaleksey.networkutils.AndroidNetworkStatus
import com.volokhinaleksey.networkutils.NetworkStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * Fragment base class for creating your fragments based on it
 */

abstract class BaseFragment<T : Any> : Fragment() {

    /**
     * Some kind of ViewModel that inherits from the base ViewModel
     */

    abstract val viewModel: BaseViewModel<T>

    /**
     * A method for processing data coming from outside
     */

    abstract fun renderData(state: T)

    protected var isNetworkAvailable: Boolean = false
    private var networkStatus: NetworkStatus? = null

    private val scope = CoroutineScope(Dispatchers.IO)

    /**
     * Method for showing the loading status
     */

    abstract fun showViewOnLoading()

    /**
     * Method for showing the error status
     */

    abstract fun showViewOnError(error: String)

    /**
     * Method for showing the success status
     */

    abstract fun showViewOnSuccess()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkStatus = AndroidNetworkStatus(requireContext())
        scope.launch {
            networkStatus?.networkObserve()?.collect {
                isNetworkAvailable = it
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}