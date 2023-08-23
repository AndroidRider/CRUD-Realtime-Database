package com.androidrider.crudrealtimeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidrider.crudrealtimeadmin.Vehicle.VehicleActivity
import com.androidrider.crudrealtimeadmin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainUpload.setOnClickListener {
            startActivity(Intent(this, UploadActivity::class.java))
            finish()
        }
        binding.mainUpdate.setOnClickListener {
            startActivity(Intent(this, UpdateActivity::class.java))
            finish()
        }
        binding.mainDelete.setOnClickListener {
            startActivity(Intent(this, DeleteActivity::class.java))
            finish()
        }

        binding.tvViewAllData.setOnClickListener {
            startActivity(Intent(this, VehicleActivity::class.java))
        }
    }
}