package com.example.swachhsathi.ui.adapter



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swachhsathi.data.model.Complaint
import com.example.swachhsathi.databinding.ListItemComplaintBinding

class ComplaintAdapter(
    private var complaints: List<Complaint>,
    private val onItemClick: (Complaint) -> Unit
) : RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder>() {

    inner class ComplaintViewHolder(val binding: ListItemComplaintBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(complaint: Complaint) {
            binding.tvTitle.text = complaint.title
            binding.tvDescription.text = complaint.description
            binding.tvStatus.text = complaint.status
            // Set up image loading using Glide or Coil if needed
            binding.root.setOnClickListener { onItemClick(complaint) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplaintViewHolder {
        val binding = ListItemComplaintBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ComplaintViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComplaintViewHolder, position: Int) {
        holder.bind(complaints[position])
    }

    override fun getItemCount() = complaints.size

    fun updateList(newComplaints: List<Complaint>) {
        complaints = newComplaints
        notifyDataSetChanged()
    }
}
