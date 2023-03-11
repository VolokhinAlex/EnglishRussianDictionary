package com.volokhinaleksey.dictionaryofwords.ui.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * The class of the basic adapter, thanks to which you can easily create lists based on RecyclerView.
 * The class contains a list of elements with some type, as well as a way to populate this list.
 */

abstract class BaseAdapter<T, V : ViewBinding, VH : BaseViewHolder<T, V>> :
    RecyclerView.Adapter<VH>() {

    /**
     * List of list items
     */

    protected var currentList: List<T> = emptyList()
        private set

    /**
     * Method for filling the list with values from outside
     * @param list - Data to be written to the current adapter list
     */

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<T>) {
        currentList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

}
