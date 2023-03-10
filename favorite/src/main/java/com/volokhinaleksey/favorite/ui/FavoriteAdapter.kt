package com.volokhinaleksey.favorite.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.volokhinaleksey.core.databinding.ItemWordBinding
import com.volokhinaleksey.core.ui.base.BaseAdapter
import com.volokhinaleksey.core.ui.base.BaseViewHolder
import com.volokhinaleksey.models.ui.FavoriteWord

/**
 * Adapter class for a list of favorite words
 */

class FavoriteAdapter(
    private val onItemClickListener: (FavoriteWord) -> Unit
) : BaseAdapter<FavoriteWord, ItemWordBinding, FavoriteAdapter.ViewHolder>() {

    /**
     * ViewHolder for filling elements with data
     */

    inner class ViewHolder(private val binding: ItemWordBinding) :
        BaseViewHolder<FavoriteWord, ItemWordBinding>(binding) {

        override fun bind(data: FavoriteWord) {
            binding.word.text = data.word
            binding.descriptionWord.text = data.meanings.joinToString { it.translation.translation }
        }

    }

    /**
     * Method for removing an item from the list
     * @param position - The position of the item in the list to be deleted
     */

    fun removeItem(position: Int) {
        currentList.removeAt(index = position)
        notifyItemRemoved(position)
    }

    /**
     * Method for creating a ViewHolder
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                onItemClickListener(currentList[layoutPosition])
            }
        }
    }

}

/**
 * CallBack for adding and processing swipes of items in the list
 */

abstract class SwipeToDeleteCallback : ItemTouchHelper.Callback() {

    /**
     * Method of setting up motion tracking
     */

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(0, swipeFlag)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

}
