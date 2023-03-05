package com.volokhinaleksey.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.volokhinaleksey.core.ui.DATA_KEY
import com.volokhinaleksey.core.ui.base.BaseFragment
import com.volokhinaleksey.core.ui.imageloader.ImageLoader
import com.volokhinaleksey.models.states.WordsState
import com.volokhinaleksey.search.databinding.FragmentDictionaryOfWordsBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DictionaryOfWordsFragment : BaseFragment<WordsState>() {

    private var _binding: FragmentDictionaryOfWordsBinding? = null
    private val binding: FragmentDictionaryOfWordsBinding get() = _binding!!

    private val imageLoader: ImageLoader<ImageView> by inject()

    private val dictionaryOfWordsAdapter: DictionaryOfWordsAdapter by lazy {
        DictionaryOfWordsAdapter(imageLoader = imageLoader) {
            requireView().findNavController().navigate(
                com.volokhinaleksey.core.R.id.description_nav_graph,
                bundleOf(DATA_KEY to it)
            )
        }
    }

    override val viewModel: com.volokhinaleksey.search.viewmodel.DictionaryOfWordsViewModel by viewModel()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDictionaryOfWordsBinding.inflate(inflater)
        binding.wordsList.layoutManager = LinearLayoutManager(requireContext())
        binding.wordsList.adapter = dictionaryOfWordsAdapter
        viewModel.currentData.observe(viewLifecycleOwner) { renderData(it) }
        binding.searchEditText
            .textChanges()
            .filterNot { it.isNullOrBlank() }
            .debounce(500)
            .onEach {
                viewModel.getWordMeanings(
                    word = it.toString(),
                    isOnline = isNetworkAvailable
                )
            }.launchIn(lifecycleScope)
        return binding.root
    }

    /**
     * Method of processing states of the [WordsState] class coming from outside
     */

    override fun renderData(state: WordsState) {
        when (state) {
            is WordsState.Error -> showViewOnError(error = state.error.localizedMessage.orEmpty())
            WordsState.Loading -> showViewOnLoading()
            is WordsState.Success -> {
                val words = state.wordData
                showViewOnSuccess()
                dictionaryOfWordsAdapter.submitList(words)
            }
        }
    }

    /**
     * Method for showing the loading status
     */

    override fun showViewOnLoading() {
        binding.baseView.errorMessage.visibility = View.GONE
        binding.wordsList.visibility = View.GONE
        binding.baseView.reloadButton.visibility = View.GONE
        binding.baseView.progressBar.visibility = View.VISIBLE
    }

    /**
     * Method for showing the error status
     */

    override fun showViewOnError(error: String) {
        binding.wordsList.visibility = View.GONE
        binding.baseView.progressBar.visibility = View.GONE
        binding.baseView.errorMessage.visibility = View.VISIBLE
        binding.baseView.reloadButton.visibility = View.VISIBLE
        binding.baseView.errorMessage.text = error
    }

    /**
     * Method for showing the success status
     */

    override fun showViewOnSuccess() {
        binding.wordsList.visibility = View.VISIBLE
        binding.baseView.errorMessage.visibility = View.GONE
        binding.baseView.progressBar.visibility = View.GONE
        binding.baseView.reloadButton.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}