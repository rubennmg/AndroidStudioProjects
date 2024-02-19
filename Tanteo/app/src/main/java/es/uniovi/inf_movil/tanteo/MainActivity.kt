package es.uniovi.inf_movil.tanteo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.LocaleList
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import es.uniovi.inf_movil.tanteo.databinding.ActivityMainBinding

const val LOCAL = "puntosLocal"
const val VISIT = "puntosVisit"

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contadorVM: TanteoVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        contadorVM = ViewModelProvider(this).get(TanteoVM::class.java)
        setContentView(binding.root) // modified

        binding.btnLocal1.setOnClickListener(this)
        binding.btnLocal2.setOnClickListener(this)
        binding.btnLocal3.setOnClickListener(this)
        binding.btnVisit1.setOnClickListener(this)
        binding.btnVisit2.setOnClickListener(this)
        binding.btnVisit3.setOnClickListener(this)
        binding.btnMain2.setOnClickListener(this)

        restauraID(contadorVM.scoreLocal, contadorVM.scoreVisit)
    }

    override fun onClick(v: View?) {
        // switch
        when (v?.id) {
            binding.btnLocal1.id -> contadorVM.scoreLocal.de1 += 1
            binding.btnLocal2.id -> contadorVM.scoreLocal.de2 += 1
            binding.btnLocal3.id -> contadorVM.scoreLocal.de3 += 1
            binding.btnVisit1.id -> contadorVM.scoreVisit.de1 += 1
            binding.btnVisit2.id -> contadorVM.scoreVisit.de2 += 1
            binding.btnVisit3.id -> contadorVM.scoreVisit.de3 += 1
            binding.btnMain2.id -> {
                Intent(this, MainActivity2::class.java).apply {
                    putExtra(LOCAL, contadorVM.scoreLocal)
                    putExtra(VISIT, contadorVM.scoreVisit)
                    startActivity(this)
                }
            }
        }

        restauraID(contadorVM.scoreLocal, contadorVM.scoreVisit)
    }

    private fun restauraID(local: TeamPoints, visit: TeamPoints) {
        binding.txtPuntLocal.text = local.total.toString()
        binding.txtMas1.text = local.de1.toString()
        binding.txtMas2.text = local.de2.toString()
        binding.txtMas3.text = local.de3.toString()

        binding.txtPuntVisit.text = visit.total.toString()
        binding.txtMas1visit.text = visit.de1.toString()
        binding.txtMas2visit.text = visit.de2.toString()
        binding.txtMas3visit.text = visit.de3.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("localDe1", contadorVM.scoreLocal.de1)
        outState.putInt("localDe2", contadorVM.scoreLocal.de2)
        outState.putInt("localDe3", contadorVM.scoreLocal.de3)

        outState.putInt("visitDe1", contadorVM.scoreVisit.de1)
        outState.putInt("visitDe2", contadorVM.scoreVisit.de2)
        outState.putInt("visitDe3", contadorVM.scoreVisit.de3)

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        contadorVM.scoreLocal.de1 = savedInstanceState.getInt("localDe1", 0)
        contadorVM.scoreLocal.de2 = savedInstanceState.getInt("localDe2", 0)
        contadorVM.scoreLocal.de3 = savedInstanceState.getInt("localDe3", 0)

        contadorVM.scoreVisit.de1 = savedInstanceState.getInt("visitDe1", 0)
        contadorVM.scoreVisit.de2 = savedInstanceState.getInt("visitDe2", 0)
        contadorVM.scoreVisit.de3 = savedInstanceState.getInt("visitDe3", 0)

        this.restauraID(contadorVM.scoreLocal, contadorVM.scoreVisit)
    }

}