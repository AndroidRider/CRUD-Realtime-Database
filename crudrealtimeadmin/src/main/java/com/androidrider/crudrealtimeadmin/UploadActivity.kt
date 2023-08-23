package com.androidrider.crudrealtimeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androidrider.crudrealtimeadmin.databinding.ActivityMainBinding
import com.androidrider.crudrealtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {

    lateinit var binding: ActivityUploadBinding

    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val ownerName = binding.edtOwnerName.text.toString()
            val vehicleBrand = binding.edtVehicleBrand.text.toString()
            val vehicleRTO = binding.edtVehicleRTO.text.toString()
            val vehicleNumber = binding.edtVehicleNumber.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
            val vehicleData = VehicleData(ownerName,vehicleBrand,vehicleRTO,vehicleNumber)
            databaseReference.child(vehicleNumber).setValue(vehicleData)
                .addOnSuccessListener {
                binding.edtOwnerName.text.clear()
                binding.edtVehicleBrand.text.clear()
                binding.edtVehicleRTO.text.clear()
                binding.edtVehicleNumber.text.clear()

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@UploadActivity, MainActivity::class.java))
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}