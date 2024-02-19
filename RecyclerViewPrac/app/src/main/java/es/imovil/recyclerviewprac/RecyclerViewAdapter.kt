package es.imovil.recyclerviewprac

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.imovil.recyclerviewprac.databinding.ItemLayoutBinding

class RecyclerViewAdapter(private var courses: MutableList<Course>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    /**
     * Retorna una clase ViewHolder que contiene la vista creada
     *
     * @param parent el ViewGroup en el que se va a mostrar la nueva vista
     * @param viewType el tipo de vista
     *
     * @return una nueva clase ViewHolder que contiene la vista creada
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    /**
     * Coloca los datos de la aplicación en la vista adecuada
     *
     * @param holder el ViewHolder que debe ser actualizado
     * @param position la posición del elemento en la lista de datos
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // obtener el curso actual
        val course = courses[position]
        // colocar el nombre del curso y el profesor en el viewholder
        holder.bind(course)
    }

    /**
     * Retorna el número total de elementos en el conjunto de datos que el adaptador maneja
     *
     * @return número total de elementos del adaptador
     */
    override fun getItemCount(): Int {
        return courses.size
    }

    /**
     * Añade un curso a la lista de cursos
     *
     * @param course el curso que se va a añadir
     */
    fun addCourse(course: Course) {
        courses.add(course)
        notifyItemInserted(courses.size - 1) // mejor que notifyDataSetChanged()
    }

    /**
     * Enlaza los elementos xml del layout con objetos java para ser modificados
     *
     * @param itemBinding el layout que se va a enlazar
     */
    class ViewHolder(private val itemBinding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(course: Course) {
            // inicializar los dos textview
            itemBinding.courseText.text = course.nombre
            itemBinding.teacherText.text = course.profesor
        }
    }

}