package com.example.mvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mvvm.CourseApp
import com.example.mvvm.databinding.FragmentDetailsBinding
import com.example.mvvm.domain.CourseDetailsViewModel


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    private val binding get() = _binding!!

    private val courseDetailsViewModel: CourseDetailsViewModel by viewModels {
        CourseDetailsViewModel.CourseDetailsViewModelFactory((activity?.application as CourseApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val courseName = arguments?.getString("course")
        courseDetailsViewModel.setCourse(courseName!!)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseDetailsViewModel.mCourse.observe(viewLifecycleOwner) { course ->
            binding.txtAsignatura.text = course.name
            binding.txtProfesor.text = course.teacher
            binding.txtDetalles.text = course.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}