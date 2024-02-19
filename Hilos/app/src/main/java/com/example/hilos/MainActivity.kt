package com.example.hilos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.hilos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val handler = Handler(Looper.getMainLooper())

        when(v?.id) {
            binding.myBtn.id -> Thread {
                // updating bar progress
                val runnable = object: Runnable {
                    var progress = 0
                    override fun run() {
                        binding.myProgressBar.progress = progress
                        progress += 10
                        if (progress <= 100) {
                            handler.postDelayed(this, 300) // update progress every 300 ms
                        }
                    }
                }
                // start progress bar
                handler.post(runnable)
                Thread.sleep(3000)
                binding.myLabel.post { // se puede hacer así, o usando un handler: handler.post { binding.myLabel.text = "Cambiada desde el hilo" }
                    binding.myLabel.text = "Cambiada desde el hilo"
                }
            }.start()
        }
    }
}