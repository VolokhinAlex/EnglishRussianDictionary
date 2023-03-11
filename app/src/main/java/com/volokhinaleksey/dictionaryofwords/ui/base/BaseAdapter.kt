package com.volokhinaleksey.dictionaryofwords.ui.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, V : ViewBinding, VH : BaseViewHolder<T, V>> :
    RecyclerView.Adapter<VH>() {

    protected var currentList: List<T> = emptyList()
        private set

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
