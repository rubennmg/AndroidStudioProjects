package com.example.mvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mvvm.CourseApp
import com.example.mvvm.databinding.FragmentNewCourseBinding
import com.example.mvvm.domain.CourseViewModel
import com.example.mvvm.model.Course
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewCourseFragment : Fragment() {

    private var _binding: FragmentNewCourseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val courseViewModel: CourseViewModel by viewModels {
        CourseViewModel.CourseViewModelFactory((activity?.application as CourseApp).repository)
    }

    fun getData(): Course? {
        val courseName = binding.inputAsignatura.text.toString()
        val courseTeacher = binding.inputProfesor.text.toString()
        val courseDetails = binding.inputDetalles.text.toString()

        if (courseName.isEmpty() || courseTeacher.isEmpty()) {
            return null
        }

        return Course(courseName, courseTeacher, courseDetails)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewCourseBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGuardar.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val course = getData()
                if (course != null) {
                    courseViewModel.repository.insertCourse(course)
                    findNavController().navigateUp()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}