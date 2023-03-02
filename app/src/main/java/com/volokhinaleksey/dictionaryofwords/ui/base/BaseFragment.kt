package com.volokhinaleksey.dictionaryofwords.ui.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.volokhinaleksey.dictionaryofwords.utils.AndroidNetworkStatus
import com.volokhinaleksey.dictionaryofwords.utils.NetworkStatus
import com.volokhinaleksey.dictionaryofwords.viewmodel.BaseViewModel
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

    protected var isNetworkAvailable: NetworkStatus.Status = NetworkStatus.Status.Unavailable
    protected val networkStatus: NetworkStatus by lazy {
        AndroidNetworkStatus(requireContext())
    }

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

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            networkStatus.observe().collect {
                isNetworkAvailable = it
            }
        }
    }
}