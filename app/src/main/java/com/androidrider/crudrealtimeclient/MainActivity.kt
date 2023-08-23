package com.androidrider.crudrealtimeclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androidrider.crudrealtimeclient.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSearch.setOnClickListener {
            val searchVehicle: String = binding.edtSearchVehicle.text.toString()
            if (searchVehicle.isNotEmpty()){
                readData(searchVehicle)
            }else{
                Toast.makeText(this, "Please enter the vehicle number", Toast.LENGTH_SHORT).show()
            }
        }
    }

   /* Original Code */
    fun readData(vehicleNumber: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        databaseReference.child(vehicleNumber).get().addOnSuccessListener {
            if (it.exists()){
                val ownerName = it.child("ownerName").value
                val vehicleBrand = it.child("vehicleBrand").value
                val vehicleRTO = it.child("vehicleRTO").value

                Toast.makeText(this, "Result Found", Toast.LENGTH_SHORT).show()
                binding.edtSearchVehicle.text.clear()

                binding.tvReadOwnerName.text = ownerName.toString()
                binding.tvReadVehicleBrand.text = vehicleBrand.toString()
                binding.tvReadVehicleRTO.text = vehicleRTO.toString()
            }else{
                Toast.makeText(this, "Vehicle number does not exists", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }


    /* with upper lower case search facility */
//    fun readData(vehicleNumber: String) {
//        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
//
//        val query = databaseReference.orderByKey()
//
//        query.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val normalizedSearchQuery = vehicleNumber.replace(" ", "").toUpperCase()
//
//                for (vehicleDataSnapshot in snapshot.children) {
//                    val key = vehicleDataSnapshot.key
//
//                    if (key?.replace(" ", "")?.toUpperCase() == normalizedSearchQuery) {
//                        val ownerName = vehicleDataSnapshot.child("ownerName").value
//                        val vehicleBrand = vehicleDataSnapshot.child("vehicleBrand").value
//                        val vehicleRTO = vehicleDataSnapshot.child("vehicleRTO").value
//
//                        Toast.makeText(this@MainActivity, "Result Found", Toast.LENGTH_SHORT).show()
//                        binding.edtSearchVehicle.text.clear()
//                        binding.tvReadOwnerName.text = ownerName.toString()
//                        binding.tvReadVehicleBrand.text = vehicleBrand.toString()
//                        binding.tvReadVehicleRTO.text = vehicleRTO.toString()
//
//                        return
//                    }
//                }
//
//                Toast.makeText(this@MainActivity, "Vehicle number does not exist", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}