package es.imovil.recyclerviewprac

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import es.imovil.recyclerviewprac.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var launchActivity2: ActivityResultLauncher<Intent>
    private lateinit var courseAdapter: CourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val asignaturas: Array<String> = resources.getStringArray(R.array.asignaturas)
        val profesores: Array<String> = resources.getStringArray(R.array.profesores)
        val cursos: List<Course> = Course.createCoursesList(asignaturas, profesores)

        adapter = RecyclerViewAdapter(cursos.toMutableList())
        courseAdapter = CourseAdapter() // using courseAdapter (ListAdapter)s
        courseAdapter.submitList(cursos) // using courseAdapter (ListAdapter)
        binding.content.recycler.adapter = courseAdapter // using courseAdapter (ListAdapter)
        binding.content.recycler.layoutManager = LinearLayoutManager(this)
        binding.content.recycler.setHasFixedSize(true)

        binding.fab.setOnClickListener {
            val course = Course("Nueva asignatura", "Nuevo profesor")
            // adapter.addCourse(course)
            // courseAdapter.addCourse(course) // using courseAdapter (ListAdapter)
            courseAdapter.submitList(cursos.slice((1..5).step(2))) // using courseAdapter (ListAdapter)
        }

        launchActivity2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val subjectName = data?.getStringExtra("SUBJECTNAME")
                val teacherName = data?.getStringExtra("TEACHERNAME")
                val course = Course(subjectName.toString(), teacherName.toString())

                // adapter.addCourse(course)
                courseAdapter.addCourse(course) // using courseAdapter (ListAdapter)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true

            R.id.add_course -> {
                val intent = Intent(this, Activity2::class.java)
                launchActivity2.launch(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}