package es.imovil.recyclerviewprac

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.ListAdapter
import es.imovil.recyclerviewprac.databinding.ItemLayoutBinding

class CourseAdapter : ListAdapter<Course, RecyclerViewAdapter.ViewHolder>(Course.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerViewAdapter.ViewHolder  {
        val itemBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewAdapter.ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course)
    }

    fun addCourse(course: Course) {
        val courses = currentList.toMutableList()
        courses.add(course)
        submitList(courses)
    }

}