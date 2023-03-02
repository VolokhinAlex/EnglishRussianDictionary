package com.volokhinaleksey.dictionaryofwords.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.volokhinaleksey.dictionaryofwords.databinding.FragmentFavoriteWordsBinding
import com.volokhinaleksey.dictionaryofwords.model.remote.WordDTO
import com.volokhinaleksey.dictionaryofwords.states.FavoriteState
import com.volokhinaleksey.dictionaryofwords.ui.base.BaseFragment
import com.volokhinaleksey.dictionaryofwords.viewmodel.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteWordsFragment : BaseFragment<FavoriteState>() {

    private var _binding: FragmentFavoriteWordsBinding? = null
    private val binding get() = _binding!!

    private val favoriteAdapter: FavoriteAdapter by lazy {
        FavoriteAdapter {
            val action =
                FavoriteWordsFragmentDirections.actionFavoriteWordsFragmentToWordDescriptionFragment(
                    wordData = WordDTO(
                        id = it.wordId,
                        text = it.word,
                        meanings = it.meanings
                    )
                )
            requireView().findNavController().navigate(action)
        }
    }

    override val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteWordsBinding.inflate(inflater)
        binding.favoriteWordsList.layoutManager = LinearLayoutManager(requireContext())
        binding.favoriteWordsList.adapter = favoriteAdapter
        viewModel.getFavorites()
        viewModel.favoriteData.observe(viewLifecycleOwner) {
            renderData(state = it)
        }
        return binding.root
    }

    /**
     * Method for showing the loading status
     */

    override fun showViewOnLoading() {
        binding.baseView.errorMessage.visibility = View.GONE
        binding.favoriteWordsList.visibility = View.GONE
        binding.baseView.reloadButton.visibility = View.GONE
        binding.baseView.progressBar.visibility = View.VISIBLE
    }

    /**
     * Method for showing the error status
     */

    override fun showViewOnError(error: String) {
        binding.favoriteWordsList.visibility = View.GONE
        binding.baseView.progressBar.visibility = View.GONE
        binding.baseView.errorMessage.visibility = View.VISIBLE
        binding.baseView.reloadButton.visibility = View.VISIBLE
        binding.baseView.errorMessage.text = error
    }

    /**
     * Method for showing the success status
     */

    override fun showViewOnSuccess() {
        binding.favoriteWordsList.visibility = View.VISIBLE
        binding.baseView.errorMessage.visibility = View.GONE
        binding.baseView.progressBar.visibility = View.GONE
        binding.baseView.reloadButton.visibility = View.GONE
    }

    override fun renderData(state: FavoriteState) {
        when (state) {
            is FavoriteState.Error -> showViewOnError(error = state.error.localizedMessage.orEmpty())
            FavoriteState.Loading -> showViewOnLoading()
            is FavoriteState.Success -> {
                val words = state.favoriteWord
                favoriteAdapter.submitList(words)
            }
        }
    }
}