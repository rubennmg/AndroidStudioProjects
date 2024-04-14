package es.imovil.practicapersistencia

import android.app.Application
import android.provider.Telephony.Mms.Part.FILENAME
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class BookViewModel(private val application: Application) : AndroidViewModel(application) {
    var bookList = mutableListOf<Book>()
    var filename: String = ""
    val database: BookDatabase = BookDatabase.getInstance(application.applicationContext)

    fun getListSize(): Int {
        return bookList.size
    }

    fun getBook(position: Int) {
        bookList[position]
    }

    fun addBook(book: Book) {
        bookList.add(book)
    }

    fun restoreBookList() {
        val file = File(application.filesDir, FILENAME)
        var list: List<Book> = emptyList()

        if (file.exists()) {
            val content = file.readText()
            val listType = object : TypeToken<List<Book>>() {}.type
            list = Gson().fromJson(content, listType)
            bookList.clear() // borrar la lista actual para que no se dupliquen los elementos
            bookList.addAll(list)
        }
    }

    fun saveBookList() {
        val file = File(application.filesDir, FILENAME)
        val content = Gson().toJson(bookList)
        file.writeText(content)
    }

    fun changeLocalization(newLocalization: String) {
        // TODO: Implement function
    }


    // --------- FUNCTIONS FOR ROOM DATABASE ---------

    fun insertBookInDB(book: Book) {
        CoroutineScope(Dispatchers.Default).launch {
            database.bookDAO().insertBook(book)
        }
    }

    fun restoreBookListFromDB() {
        CoroutineScope(Dispatchers.Default).launch {
            val libros = database.bookDAO().getAllBooks()
            bookList.addAll(libros)
        }
    }

    fun saveBookListInDB() {
        CoroutineScope(Dispatchers.Default).launch {
            database.bookDAO().saveAllBooks(bookList)
        }
    }
}