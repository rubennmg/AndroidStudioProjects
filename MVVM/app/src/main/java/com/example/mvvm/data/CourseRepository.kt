package com.example.mvvm.data

import com.example.mvvm.model.Course
import com.example.mvvm.model.CourseDAO
import kotlinx.coroutines.flow.Flow

/**
 * Encapsula los accesos a todas las fuentes de datos de la aplicación
 */
class CourseRepository(private val courseDAO: CourseDAO) {

    fun getCourseNames() = courseDAO.getNames()

    fun getCourseByName(courseName: String): Flow<Course> {
        return courseDAO.getCourseByName(courseName)
    }

    suspend fun insertCourse(course: Course) = courseDAO.insertCourse(course)

    suspend fun deleteCourse(courseName: String) = courseDAO.deleteCourse(courseName)

}