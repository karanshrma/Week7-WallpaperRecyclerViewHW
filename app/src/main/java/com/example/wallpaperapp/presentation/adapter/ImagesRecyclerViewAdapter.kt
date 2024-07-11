package com.example.wallpaperapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaperapp.R
import com.example.wallpaperapp.domain.entity.WallpaperLink


class ImagesRecyclerViewAdapter(
    private val wallpaperList: List<WallpaperLink>, private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<ImagesRecyclerViewAdapter.WallpaperViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)
        return WallpaperViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        val currentItem = wallpaperList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return wallpaperList.size
    }

    inner class WallpaperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: AppCompatImageView = itemView.findViewById(R.id.ImageView)

        fun bind(currentItem: WallpaperLink) {
            Glide.with(imageView.context).load(currentItem.url).into(imageView)
            imageView.setOnClickListener {
                onItemClick(currentItem.url)
            }

        }
    }
}
