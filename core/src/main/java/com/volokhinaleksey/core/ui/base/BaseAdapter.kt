package com.volokhinaleksey.core.ui.base

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

    protected var currentList: MutableList<T> = mutableListOf()
        private set

    /**
     * Method for filling the list with values from outside
     * @param list - Data to be written to the current adapter list
     */

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<T>) {
        currentList = list.toMutableList()
        notifyDataSetChanged()
    }

    /**
     * Method for filling in list items
     */

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(currentList[position])
    }

    /**
     * Method for getting the size of the data list
     */

    override fun getItemCount(): Int = currentList.size

}
