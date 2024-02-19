package es.imovil.recyclerviewprac

import android.util.Log
import androidx.recyclerview.widget.DiffUtil

data class Course (var nombre: String, var profesor: String) {

    companion object {
        fun createCoursesList(asignaturas: Array<String>, profesores: Array<String>): List<Course> {
            val courses = mutableListOf<Course>()
            if (asignaturas.size  == profesores.size) {
                // se añade a la lista un objeto Course tomando un elemento del
                // array de asignaturas y otro del array de profesores
                // sugerencia: usar la función zip (combina dos listas en una lista de pares)
                for ((asignatura, profesor) in asignaturas.zip(profesores)) {
                    courses.add(Course(asignatura, profesor))
                }
            }
            return courses.toList()
        }

        fun createCourseList(asignaturas: Array<String>, profesores: Array<String>): List<Course> {
            // Añadimos a la lista un objeto Course tomando un elemento el array de asignaturas y otro de profesores
            val courses = mutableListOf<Course>()
            if (asignaturas.size == profesores.size) {
                for (i in asignaturas.indices) {
                    courses.add(Course(asignaturas[i], profesores[i]))
                }
            }
            return courses.toList()
        }
    }

    object DIFF_CALLBACK: DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }

    }

    override fun toString(): String {
        return "[Curso: $nombre], [Profesor: $profesor]"
    }

}
