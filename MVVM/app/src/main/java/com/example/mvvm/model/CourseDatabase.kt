package com.example.mvvm.model

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Clase que representa la base de datos de la aplicación
 * En este caso sigue el patrón Singleton y, en su creación o apertura,
 * se invoca un callback que inserta tres cursos de ejemplo
 */
@Database(entities = [Course::class], version = 1)
abstract class CourseDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDAO

    companion object {
        private var INSTANCE: CourseDatabase? = null

        fun getInstance(context: Context): CourseDatabase? {
            if (INSTANCE == null) {
                synchronized(CourseDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CourseDatabase::class.java, "course.db"
                    )
                        .addCallback(CALLBACK)
                        .build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        private val CALLBACK = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                CoroutineScope(Dispatchers.IO).launch {
                    INSTANCE!!.courseDao().insertCourse(
                        Course(
                            "Informática Móvil",
                            "José Ramón Arias",
                            "Asignatura dedicada a la programación de aplicaciones móviles en Android"
                        )
                    )

                    INSTANCE!!.courseDao().insertCourse(
                        Course(
                            "Proyectos",
                            "Vicente",
                            "Asignatura dedicada a la gestión de proyectos"
                        )
                    )

                    INSTANCE!!.courseDao().insertCourse(
                        Course(
                            "Sistemas Distribuidos",
                            "Jose Luis Díaz de Arriba",
                            "Asignatura dedicada a la programación de sistemas distribuidos"
                        )
                    )
                }
            }
        }
    }

}