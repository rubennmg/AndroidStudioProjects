package es.uniovi.inf_movil.primera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button

private const val TAG = "Actividad 1"

class MainActivity : AppCompatActivity() {

    val button: Button by lazy {
        findViewById(R.id.button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Estoy en onCreate()")
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            Log.d(TAG, "Bundle es null")
        else
            Log.d(TAG, "Bundle es not null")

        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Estoy en onStart()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Estoy en onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Estoy en onDestroy()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Estoy en onResume()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "Estoy en onReStart()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Estoy en onPause()")
        Thread.sleep(3000) // 3 secs to change to activity 2
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "Estoy en onSaveInstanceState(), state: $outState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "Estoy en onRestoreInstanceState(), state: $savedInstanceState")
    }
}