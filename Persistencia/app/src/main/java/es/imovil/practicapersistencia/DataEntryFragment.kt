package es.imovil.practicapersistencia

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import es.imovil.practicapersistencia.databinding.FragmentDataEntryBinding
import kotlin.properties.Delegates


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DataEntryFragment : Fragment() {

    private var _binding: FragmentDataEntryBinding? = null
    private val bookViewModel: BookViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDataEntryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            bookViewModel.addBook(getData())
            Navigation.findNavController(it).popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getData() : Book {
        lateinit var autor: String
        lateinit var titulo: String
        var isbntmp: String?
        var editorial: String?
        var preciotmp: Float

        with (binding) {
            autor = authorEdit.text.toString()
            titulo = titleEdit.text.toString()
            isbntmp = isbn.text.toString()
            editorial = editorEdit.text.toString()
            preciotmp = price.text.toString().toFloat()
        }

        return Book(titulo, autor, isbntmp, editorial, preciotmp)
    }
}