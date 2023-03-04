package com.volokhinaleksey.dictionaryofwords.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.volokhinaleksey.core.ui.base.BaseFragment
import com.volokhinaleksey.dictionaryofwords.databinding.FragmentHistorySearchBinding
import com.volokhinaleksey.core.ui.imageloader.ImageLoader
import com.volokhinaleksey.dictionaryofwords.viewmodel.HistoryViewModel
import com.volokhinaleksey.models.states.WordsState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HistorySearchFragment : BaseFragment<WordsState>() {

    private var _binding: FragmentHistorySearchBinding? = null
    private val binding get() = _binding!!
    private val imageLoader: ImageLoader<ImageView> by inject()

    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter(imageLoader = imageLoader) {
            val action =
                HistorySearchFragmentDirections.actionHistorySearchFragmentToWordDescriptionFragment(
                    wordData = it
                )
            requireView().findNavController().navigate(action)
        }
    }

    override val viewModel: HistoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistorySearchBinding.inflate(inflater)
        binding.historyWordsList.layoutManager = LinearLayoutManager(requireContext())
        binding.historyWordsList.adapter = historyAdapter
        viewModel.getHistory()
        viewModel.currentData.observe(viewLifecycleOwner) {
            renderData(state = it)
        }
        return binding.root
    }


    /**
     * Method for showing the loading status
     */

    override fun showViewOnLoading() {
        binding.baseView.errorMessage.visibility = View.GONE
        binding.historyWordsList.visibility = View.GONE
        binding.baseView.reloadButton.visibility = View.GONE
        binding.baseView.progressBar.visibility = View.VISIBLE
    }

    /**
     * Method for showing the error status
     */

    override fun showViewOnError(error: String) {
        binding.historyWordsList.visibility = View.GONE
        binding.baseView.progressBar.visibility = View.GONE
        binding.baseView.errorMessage.visibility = View.VISIBLE
        binding.baseView.reloadButton.visibility = View.VISIBLE
        binding.baseView.errorMessage.text = error
    }

    /**
     * Method for showing the success status
     */

    override fun showViewOnSuccess() {
        binding.historyWordsList.visibility = View.VISIBLE
        binding.baseView.errorMessage.visibility = View.GONE
        binding.baseView.progressBar.visibility = View.GONE
        binding.baseView.reloadButton.visibility = View.GONE
    }

    override fun renderData(state: WordsState) {
        when (state) {
            is WordsState.Error -> showViewOnError(error = state.error.localizedMessage.orEmpty())
            WordsState.Loading -> showViewOnLoading()
            is WordsState.Success -> {
                val words = state.wordData
                showViewOnSuccess()
                historyAdapter.submitList(words)
            }
        }
    }
}