package com.example.fragmentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import com.example.fragmentos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var isChanged: Boolean = false
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val listener: FragmentResultListener = FragmentResultListener { _, bundle ->
        binding.label.text = bundle.getString("bundleKeyAct")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener() {
            supportFragmentManager.commit {
                isChanged = if (isChanged) {
                    replace(binding.fragmentContainerView1.id, FirstFragment())
                    replace(binding.fragmentContainerView2.id, SecondFragment())
                    false
                } else {
                    replace(binding.fragmentContainerView1.id, SecondFragment())
                    replace(binding.fragmentContainerView2.id, FirstFragment())
                    true
                }
            }
        }

        // listener para el resultado del segundo fragmento
        supportFragmentManager.setFragmentResultListener("requestKeyAct", this, listener)
    }
}