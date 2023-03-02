package com.volokhinaleksey.dictionaryofwords.ui.dictionarywords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.volokhinaleksey.dictionaryofwords.databinding.FragmentDictionaryOfWordsBinding
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import com.volokhinaleksey.dictionaryofwords.ui.base.BaseFragment
import com.volokhinaleksey.dictionaryofwords.ui.imageloaders.ImageLoader
import com.volokhinaleksey.dictionaryofwords.ui.textChanges
import com.volokhinaleksey.dictionaryofwords.utils.NetworkStatus
import com.volokhinaleksey.dictionaryofwords.viewmodel.DictionaryOfWordsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
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
            val action = DictionaryOfWordsFragmentDirections
                .actionDictionaryOfWordsFragmentToWordDescriptionFragment(wordData = it)
            requireView().findNavController().navigate(action)
        }
    }

    override val viewModel: DictionaryOfWordsViewModel by viewModel()

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
                    isOnline =  when (isNetworkAvailable) {
                        NetworkStatus.Status.Available -> true
                        else -> false
                    }
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