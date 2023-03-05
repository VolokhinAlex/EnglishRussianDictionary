package com.volokhinaleksey.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.volokhinaleksey.core.ui.DATA_KEY
import com.volokhinaleksey.core.ui.base.BaseFragment
import com.volokhinaleksey.favorite.databinding.FragmentFavoriteWordsBinding
import com.volokhinaleksey.models.remote.FavoriteWord
import com.volokhinaleksey.models.states.FavoriteState
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteWordsFragment : BaseFragment<FavoriteState>() {

    private var _binding: FragmentFavoriteWordsBinding? = null
    private val binding get() = _binding!!

    private val favoriteAdapter: FavoriteAdapter by lazy {
        FavoriteAdapter {
            requireView().findNavController().navigate(
                com.volokhinaleksey.core.R.id.description_nav_graph,
                bundleOf(DATA_KEY to it)
            )
        }
    }

    override val viewModel: com.volokhinaleksey.favorite.viewmodel.FavoriteViewModel by viewModel()

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
                val favoriteWords = state.favoriteWord
                favoriteAdapter.submitList(favoriteWords)
                swipeToDeleteFavoriteWord(favoriteWords)
            }
        }
    }

    private fun swipeToDeleteFavoriteWord(favoriteWord: List<FavoriteWord>) {
        val swipeToDirections = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteFavoriteWord(FavoriteState.Success(listOf(favoriteWord[position])))
                favoriteAdapter.removeItem(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDirections)
        itemTouchHelper.attachToRecyclerView(binding.favoriteWordsList)
    }
}