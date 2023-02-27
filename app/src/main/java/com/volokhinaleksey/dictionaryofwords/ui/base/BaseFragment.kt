package com.volokhinaleksey.dictionaryofwords.ui.base

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.volokhinaleksey.dictionaryofwords.utils.AndroidNetworkStatus
import com.volokhinaleksey.dictionaryofwords.utils.NetworkStatus
import com.volokhinaleksey.dictionaryofwords.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

abstract class BaseFragment<T : Any> : Fragment() {
    abstract val viewModel: BaseViewModel<T>
    abstract fun renderData(state: T)

    protected var isNetworkAvailable: Boolean = false
    protected var networkStatus: NetworkStatus? = null


    abstract fun showViewOnLoading()

    abstract fun showViewOnError(error: String)

    abstract fun showViewOnSuccess()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkStatus = AndroidNetworkStatus(requireContext())
//        lifecycleScope.launch {
//            networkStatus?.isNetworkAvailable()?.collect {
//                isNetworkAvailable = it
//            }
//        }
    }
}