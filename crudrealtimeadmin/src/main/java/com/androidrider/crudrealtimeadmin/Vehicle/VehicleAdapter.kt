package com.androidrider.crudrealtimeadmin.Vehicle

// VehicleAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidrider.crudrealtimeadmin.R

class VehicleAdapter(private val vehicles: List<VehicleModel>) :
    RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vehicle, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(vehicles[position])
    }

    override fun getItemCount(): Int {
        return vehicles.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(vehicle: VehicleModel) {

            val tvOwnerName = itemView.findViewById<TextView>(R.id.tvOwnerName)
            val tvVehicleBrand = itemView.findViewById<TextView>(R.id.tvVehicleBrand)
            val tvVehicleRTO = itemView.findViewById<TextView>(R.id.tvVehicleRTO)
            val tvVehicleNumber = itemView.findViewById<TextView>(R.id.tvVehicleNumber)

            tvOwnerName.text = vehicle.ownerName
            tvVehicleBrand.text = vehicle.vehicleBrand
            tvVehicleRTO.text = vehicle.vehicleRTO
            tvVehicleNumber.text = vehicle.vehicleNumber
        }
    }
}
