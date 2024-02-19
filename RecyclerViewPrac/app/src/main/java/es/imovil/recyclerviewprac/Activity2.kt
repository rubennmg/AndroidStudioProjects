package es.imovil.recyclerviewprac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import es.imovil.recyclerviewprac.databinding.Activity2Binding

class Activity2 : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: Activity2Binding
    private var subjectName: String = ""
    private var teacherName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Activity2Binding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.content.btnOK.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity2, menu)
        title = resources.getString(R.string.new_course)
        return true
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.content.btnOK.id -> {
                subjectName = binding.content.inputAsignatura.text.toString()
                teacherName = binding.content.inputProfesor.text.toString()

                if (subjectName.isNotEmpty() && teacherName.isNotEmpty()) {
                    val intent = intent
                    intent.putExtra("SUBJECTNAME", subjectName)
                    intent.putExtra("TEACHERNAME", teacherName)
                    setResult(RESULT_OK, intent)
                    finish()
                } else {
                    Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}