package com.example.mvvm.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.databinding.ListItemBinding

class CourseViewHolder(private val itemBinding: ListItemBinding, private val onItemClicked: (Int) ->Unit) : RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemView.setOnClickListener{
            onItemClicked(adapterPosition)
        }
    }

    fun bind (courseName: String) {
        itemBinding.courseName.text = courseName
    }

}