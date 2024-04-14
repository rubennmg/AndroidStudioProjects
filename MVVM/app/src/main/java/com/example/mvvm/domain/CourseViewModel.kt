package com.example.mvvm.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.mvvm.data.CourseRepository
import com.example.mvvm.model.Course

/**
 * Clase que define el ViewModel para la vista de cursos
 */
class CourseViewModel(val repository: CourseRepository): ViewModel() {
    val courseNames: LiveData<List<String>> by lazy {
        repository.getCourseNames().asLiveData()
    }

    /**
     * Clase Factoría para crear instancias de CourseViewModel, así se podrán pasar
     * parámetros al constructor de CourseViewModel y así poder refrenciar al repositorio
     */
    class CourseViewModelFactory(private val repository: CourseRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CourseViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CourseViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}

