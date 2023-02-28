package com.volokhinaleksey.dictionaryofwords.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.volokhinaleksey.dictionaryofwords.utils.AndroidNetworkStatus
import com.volokhinaleksey.dictionaryofwords.utils.NetworkStatus
import com.volokhinaleksey.dictionaryofwords.viewmodel.BaseViewModel

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

    protected var networkStatus: NetworkStatus? = null

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
    }
}