package com.androidrider.crudrealtimeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androidrider.crudrealtimeadmin.databinding.ActivityDeleteBinding
import com.androidrider.crudrealtimeadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    lateinit var binding: ActivityDeleteBinding
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")

        binding.btnDelete.setOnClickListener {
            val vehicleNumber = binding.edtVehicleNumber.text.toString()

            if (vehicleNumber.isNotEmpty()){
                deleteData(vehicleNumber)
            }else{
                Toast.makeText(this, "Please enter the vehicle number", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun deleteData(vehicleNumber:String){
        databaseReference.child(vehicleNumber).removeValue()
            .addOnSuccessListener {
                binding.edtVehicleNumber.text.clear()
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
                Toast.makeText(this, "Unable to delete", Toast.LENGTH_SHORT).show()
            }
    }
}