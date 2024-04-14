package com.example.mvvm.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz que define las operaciones de acceso a la base de datos
 */
@Dao
interface CourseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course:Course)

    @Query("DELETE FROM course_table WHERE name = :course")
    suspend fun deleteCourse(course:String)

    @Query("SELECT * FROM course_table WHERE name LIKE :name")
    fun getCourseByName(name:String): Flow<Course> // El tipo de dato Flow permite implementar el patrón de diseño Observer:
                                                          // los observadores serán notificados cuando haya un cambio en la base de datos
    @Query("SELECT name FROM course_table")
    fun getNames(): Flow<List<String>>

}