package com.example.mvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Clase que contiene el modelo de datos
 */
@Entity(tableName = "course_table")
data class Course(@PrimaryKey val name: String, val teacher:String, val description:String) {

}