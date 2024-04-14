package com.example.mvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mvvm.databinding.ListItemBinding
import com.example.mvvm.utils.Utils

class CourseListAdapter(private val onNameSelected: (String) -> Unit): ListAdapter<String, CourseViewHolder>(Utils.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return CourseViewHolder(itemBinding) {
            onNameSelected(getItem(it))
        }
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val courseName = getItem(position)
        holder.bind(courseName)
    }

}