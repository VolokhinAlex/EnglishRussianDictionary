package com.volokhinaleksey.history.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.volokhinaleksey.core.ui.DATA_KEY
import com.volokhinaleksey.core.ui.base.BaseFragment
import com.volokhinaleksey.core.ui.imageloader.ImageLoader
import com.volokhinaleksey.history.databinding.FragmentHistorySearchBinding
import com.volokhinaleksey.history.viewmodel.HistoryViewModel
import com.volokhinaleksey.models.states.WordsState
import org.koin.android.scope.getOrCreateScope

class HistorySearchFragment : BaseFragment<WordsState>() {

    private var _binding: FragmentHistorySearchBinding? = null
    private val binding get() = _binding!!

    private val scope = getOrCreateScope().value

    private val imageLoader: ImageLoader<ImageView> = scope.get()

    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter(imageLoader = imageLoader) {
            requireView().findNavController().navigate(
                com.volokhinaleksey.core.R.id.description_nav_graph,
                bundleOf(DATA_KEY to it)
            )
        }
    }

    override val viewModel: HistoryViewModel = scope.get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistorySearchBinding.inflate(inflater)
        binding.historyWordsList.layoutManager = LinearLayoutManager(requireContext())
        binding.historyWordsList.adapter = historyAdapter
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

    /**
     * Method of processing states of the [WordsState] class coming from outside
     * @param state - The state to be processed
     */

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        scope.close()
    }
}