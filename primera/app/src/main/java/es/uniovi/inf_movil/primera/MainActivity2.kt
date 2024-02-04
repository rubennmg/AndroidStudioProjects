package es.uniovi.inf_movil.primera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

private const val TAG = "Actividad 2"

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Estoy en onCreate2()")

        if (savedInstanceState == null)
            Log.d(TAG, "Bundle es null")
        else
            Log.d(TAG, "Bundle es not null")

        setContentView(R.layout.activity_main2)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Estoy en onStart2()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Estoy en onStop2()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Estoy en onDestroy2()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Estoy en onResume2()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "Estoy en onReStart2()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Estoy en onPause2()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "Estoy en onSaveInstanceState()2, state: $outState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "Estoy en onRestoreInstanceState()2, state: $savedInstanceState")
    }

}