package es.uniovi.inf_movil.tanteo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import es.uniovi.inf_movil.tanteo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var scoreLocal: TeamPoints = TeamPoints(0, 0, 0)
    private var scoreVisit: TeamPoints = TeamPoints(0, 0, 0)

    // vinculación de vistas, para así no declarar una variable para cada elemento del layout
    private val bindingMain1: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // ToDo -> método para actualizar puntaje -> updateScore()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingMain1.root) // modified

        bindingMain1.btnLocal1.setOnClickListener {
            scoreLocal.de1 += 1
            bindingMain1.txtPuntLocal.text = scoreLocal.total.toString()
            bindingMain1.txtMas1.text = scoreLocal.de1.toString()
        }

        bindingMain1.btnLocal2.setOnClickListener {
            scoreLocal.de2 += 1
            bindingMain1.txtPuntLocal.text = scoreLocal.total.toString()
            bindingMain1.txtMas2.text = scoreLocal.de2.toString()

        }

        bindingMain1.btnLocal3.setOnClickListener {
            scoreLocal.de3 += 1
            bindingMain1.txtPuntLocal.text = scoreLocal.total.toString()
            bindingMain1.txtMas3.text = scoreLocal.de3.toString()

        }

        bindingMain1.btnVisit1.setOnClickListener {
            scoreVisit.de1 += 1
            bindingMain1.txtPuntVisit.text = scoreVisit.total.toString()
            bindingMain1.txtMas1visit.text = scoreVisit.de1.toString()
        }

        bindingMain1.btnVisit2.setOnClickListener {
            scoreVisit.de2 += 1
            bindingMain1.txtPuntVisit.text = scoreVisit.total.toString()
            bindingMain1.txtMas2visit.text = scoreVisit.de2.toString()
        }

        bindingMain1.btnVisit3.setOnClickListener {
            scoreVisit.de3 += 1
            bindingMain1.txtPuntVisit.text = scoreVisit.total.toString()
            bindingMain1.txtMas3visit.text = scoreVisit.de3.toString()
        }
    }
}