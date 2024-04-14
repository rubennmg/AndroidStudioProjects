package es.imovil.practicapersistencia

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Book::class), version = 1)
abstract class BookDatabase : RoomDatabase(){
    abstract fun bookDAO(): BookDAO

    companion object {
        private var INSTANCE: BookDatabase? = null

        private val CALLBACK = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                CoroutineScope(Dispatchers.IO).launch {
                    INSTANCE!!.bookDAO().insertBook(
                        Book(
                            "El ingenioso Hidalgo Don Quijote de la Mancha",
                            "Miguel de Cervantes y Saavedra",
                            "123-456-789-X",
                            "Peregrino",
                            17.5F
                        )
                    )
                }
            }
        }

        fun getInstance(context: Context): BookDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java, "book_database"
                )
                    .addCallback(CALLBACK)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }


}