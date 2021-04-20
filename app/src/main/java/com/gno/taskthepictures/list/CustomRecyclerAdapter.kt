package com.gno.taskthepictures.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gno.taskthepictures.R

import com.squareup.picasso.Picasso

class CustomRecyclerAdapter(
    private val isTablet: Boolean,
    private val ScreenWidth: Int,
    private val cellClickListener: (String) -> Unit
) : ListAdapter<String, CustomRecyclerAdapter.DataHolder>(DataItemDiffUtilCallback()) {

    override fun getItemCount(): Int {
        return when (val count = super.getItemCount()) {
            0 -> 0
            else -> count
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        return DataHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {

        val targetWidth = if (isTablet) ScreenWidth / 3
        else ScreenWidth / 2

        holder.pictureImageView.requestLayout()

        Picasso.get().load(getItem(position)).resize(targetWidth, targetWidth)
            .into(holder.pictureImageView)

        holder.pictureImageView.setOnClickListener {
            cellClickListener.invoke(getItem(position))
        }
    }

    class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pictureImageView: ImageView = itemView.findViewById(R.id.recyclerview_item_picture)
    }

}