package com.androidrider.crudrealtimeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androidrider.crudrealtimeadmin.databinding.ActivityUpdateBinding
import com.androidrider.crudrealtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdateBinding

    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")

        binding.btnUpdate.setOnClickListener {
            val referenceVehicleNumber = binding.referenceVehicleNumber.text.toString()
            val ownerName = binding.edtOwnerName.text.toString()
            val vehicleBrand = binding.edtVehicleBrand.text.toString()
            val vehicleRTO = binding.edtVehicleRTO.text.toString()

            updateData(referenceVehicleNumber, ownerName, vehicleBrand, vehicleRTO)
        }
    }

    fun updateData(vehicleNumber:String, ownerName:String, vehicleBrand:String, vehicleRTO:String){

        val vehicleData = mapOf<String,String>("ownerName" to ownerName, "vehicleBrand" to vehicleBrand, "vehicleRTO" to vehicleRTO)
        databaseReference.child(vehicleNumber).updateChildren(vehicleData)
            .addOnSuccessListener {
            binding.referenceVehicleNumber.text.clear()
            binding.edtOwnerName.text.clear()
            binding.edtVehicleBrand.text.clear()
            binding.edtVehicleRTO.text.clear()
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this, "Unable to update", Toast.LENGTH_SHORT).show()
        }
    }

}