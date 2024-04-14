package es.imovil.practicapersistencia

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book:Book)

    @Delete
    suspend fun deleteBook(book: Book)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAllBooks(lista:List<Book>)

    @Query("SELECT * FROM book_table")
    suspend fun getAllBooks(): List<Book>


    @Query("DELETE from book_table")
    suspend fun deleteAll();

}