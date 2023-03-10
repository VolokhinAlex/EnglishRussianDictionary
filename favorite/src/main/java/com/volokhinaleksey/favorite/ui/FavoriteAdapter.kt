package com.volokhinaleksey.favorite.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.volokhinaleksey.core.databinding.ItemWordBinding
import com.volokhinaleksey.core.ui.base.BaseAdapter
import com.volokhinaleksey.core.ui.base.BaseViewHolder
import com.volokhinaleksey.models.ui.FavoriteWord

class FavoriteAdapter(
    private val onItemClickListener: (FavoriteWord) -> Unit
) : BaseAdapter<FavoriteWord, ItemWordBinding, FavoriteAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemWordBinding) :
        BaseViewHolder<FavoriteWord, ItemWordBinding>(binding) {

        override fun bind(data: FavoriteWord) {
            binding.word.text = data.word
            binding.descriptionWord.text = data.meanings.joinToString { it.translation.translation }
        }

    }

    fun removeItem(position: Int) {
        currentList.removeAt(index = position)
        notifyItemRemoved(position)
    }

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


abstract class SwipeToDeleteCallback : ItemTouchHelper.Callback() {
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
