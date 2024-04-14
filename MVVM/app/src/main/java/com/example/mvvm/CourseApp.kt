package com.example.mvvm

import android.app.Application
import com.example.mvvm.data.CourseRepository
import com.example.mvvm.model.CourseDatabase

class CourseApp:  Application() {

    val courseDatabase by lazy {
        CourseDatabase.getInstance(this)
    }
    val repository by lazy {
        CourseRepository(courseDatabase!!.courseDao())
    }

}