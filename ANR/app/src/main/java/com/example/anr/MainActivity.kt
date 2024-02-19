package com.example.anr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.anr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var contador: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btn1.id -> contador += 1
            binding.btn2.id -> Hilo().start() // Thread.sleep(20000)
        }

        binding.txtValue.text = contador.toString()
    }

}