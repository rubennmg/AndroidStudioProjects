package es.uniovi.inf_movil.tanteo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import es.uniovi.inf_movil.tanteo.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var localTeam: TeamPoints
    private lateinit var visitTeam: TeamPoints

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        localTeam = intent.getParcelableExtra(LOCAL, TeamPoints::class.java) ?: TeamPoints(0,0,0) // esto está deprecated pero funciona
        visitTeam = intent.getParcelableExtra(VISIT, TeamPoints::class.java) ?: TeamPoints(0,0,0) // ahora ya no está deprecated

        Log.d("Tanteo", "Local: ${localTeam.de1} ${localTeam.de2} ${localTeam.de3}")
        Log.d("Tanteo", "Visit: ${visitTeam.de1} ${visitTeam.de2} ${visitTeam.de3}")

        setContentView(R.layout.activity_main2)
    }

    fun updateUI(local: TeamPoints, visit: TeamPoints) {


    }

}