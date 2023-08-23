package com.androidrider.crudrealtimeadmin.Vehicle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidrider.crudrealtimeadmin.R
import com.androidrider.crudrealtimeadmin.databinding.ActivityVehicleBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// VehicleActivity.kt
class VehicleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVehicleBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var vehicleAdapter: VehicleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val vehicles = mutableListOf<VehicleModel>() // Populate this list with data from Firebase

        vehicleAdapter = VehicleAdapter(vehicles)
        recyclerView.adapter = vehicleAdapter

        // Load data from Firebase and populate the 'vehicles' list
        loadDataFromFirebase()
    }

    private fun loadDataFromFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val vehicles = mutableListOf<VehicleModel>()
                for (vehicleSnapshot in snapshot.children) {
                    val ownerName = vehicleSnapshot.child("ownerName").getValue(String::class.java)
                    val vehicleBrand = vehicleSnapshot.child("vehicleBrand").getValue(String::class.java)
                    val vehicleRTO = vehicleSnapshot.child("vehicleRTO").getValue(String::class.java)
                    val vehicleNumber = vehicleSnapshot.child("vehicleNumber").getValue(String::class.java)

                    val vehicle = VehicleModel(ownerName, vehicleBrand, vehicleRTO, vehicleNumber)
                    vehicles.add(vehicle)
                }

                vehicleAdapter = VehicleAdapter(vehicles)
                binding.recyclerView.adapter = vehicleAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}
