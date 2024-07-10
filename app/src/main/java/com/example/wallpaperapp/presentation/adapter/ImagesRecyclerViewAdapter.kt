package com.example.wallpaperapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp.R

class ImagesRecyclerViewAdapter(private var dataSet: Array<String>) :
    RecyclerView.Adapter<ImagesRecyclerViewAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView

        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.textView)
            imageView = view.findViewById(R.id.ImageView)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    fun setItems(dataSet: Array<String>) {
        this.dataSet = dataSet // one item less you will be able to -> animation
        notifyDataSetChanged() // less efficient you're chanign the entire list
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position]
        viewHolder.imageView.setImageResource(R.drawable.ic_launcher_background)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}
