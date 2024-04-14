package com.example.appsharing

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.appsharing.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import android.Manifest
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { image ->
        binding.imageView.setImageBitmap(image)
    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.shareMessage.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, binding.message.text.toString())
                setType("text/plain")
            }
            val activities= packageManager.queryIntentActivities(sendIntent, 0)
            val isIntentSafe = activities.size > 0
            if (isIntentSafe) {
                startActivity(sendIntent)
            }
        }

        binding.sendEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                setData(Uri.fromParts("mailto:", "rubenmg2015@gmail.com", null))
                putExtra(Intent.EXTRA_SUBJECT, "Prueba desde AppSharing")
                putExtra(Intent.EXTRA_TEXT, binding.message.text.toString())
            }
            val activities= packageManager.queryIntentActivities(emailIntent, 0)
            val isIntentSafe = activities.size > 0
            Log.d("MainActivity", "isIntentSafe: $isIntentSafe")
            if (isIntentSafe) {
                startActivity(emailIntent)
            }
        }

        binding.getImage.setOnClickListener {
            requestCameraPermission()
        }

        val intent = intent
        val action = intent.action

        Toast.makeText(this, "Action: $action", Toast.LENGTH_SHORT).show()

        if (Intent.ACTION_SEND == action) {
            val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
            binding.txtIntent.setText(sharedText)
        }
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Snackbar.make(binding.root, R.string.permission_camera_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok) {
                        // Intentar solicitar permiso nuevamente
                        requestCameraPermission()
                    }.show()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
            }
        } else {
            cameraDoWork()
        }
    }

    fun cameraDoWork() {
        takePictureLauncher.launch(null)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                cameraDoWork()
            } else {
                Snackbar.make(binding.root, R.string.permission_camera_denied, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 101
    }

}