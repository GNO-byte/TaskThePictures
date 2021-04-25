package com.gno.taskthepictures.list

import androidx.recyclerview.widget.DiffUtil

class DataItemDiffUtilCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem === newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

}