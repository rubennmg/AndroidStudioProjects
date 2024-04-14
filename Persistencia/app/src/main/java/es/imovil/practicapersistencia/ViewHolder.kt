package es.imovil.practicapersistencia

import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import es.imovil.practicapersistencia.databinding.ItemViewBinding

class ViewHolder(private val itemViewBinding: ItemViewBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {
    private val sp = PreferenceManager.getDefaultSharedPreferences(itemViewBinding.root.context)

    fun bind(book: Book) {
        with(itemViewBinding) {
            author.text = book.author
            title.text = book.title
            isbn.text = book.isbn
            editor.text = book.editorial
            price.text = book.price.toString()
            val isbnPref = sp.getBoolean("isbn", true)

            when (isbnPref) {
                true -> {
                    isbn.text = book.isbn ?: " "
                }
                false -> {
                    isbn.text = " "
                }
            }
        }
    }
}