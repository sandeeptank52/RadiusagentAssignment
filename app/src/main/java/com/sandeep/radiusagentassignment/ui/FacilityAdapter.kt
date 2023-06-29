package com.sandeep.radiusagentassignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.radiusagentassignment.data.models.Exclusion
import com.sandeep.radiusagentassignment.data.models.Facility
import com.sandeep.radiusagentassignment.data.models.FacilityOption
import com.sandeep.radiusagentassignment.databinding.ItemFacilityBinding

class FacilityAdapter(
    private var facilities: List<Facility>,
    private var exclusions: List<List<Exclusion>>,
) :
    RecyclerView.Adapter<FacilityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFacilityBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = facilities[position]
        holder.bind(option)
    }

    override fun getItemCount(): Int {
        return facilities.size
    }

    inner class ViewHolder(private val binding: ItemFacilityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(facility: Facility) {
            binding.facility = facility
            binding.recyclerView.adapter =
                FacilityOptionAdapter(facility.options, object : FacilityOptionCallback {
                    override fun onFacilityOptionClick(option: FacilityOption, isChecked: Boolean) {
                        handleExclusion(facility, option, isChecked)
                    }

                })
            binding.executePendingBindings()
        }

        private fun handleExclusion(facility: Facility, option: FacilityOption, checked: Boolean) {
            val selectedExclusion = Exclusion(
                facilityId = facility.facilityId,
                optionsId = option.id
            )
            val matchedList = exclusions.filter { it.contains(selectedExclusion) }
            matchedList.forEach { subList ->
                val sePair = subList.first { it != selectedExclusion }
                facilities.forEach {
                    if (it.facilityId == sePair.facilityId) {
                        it.options.forEach { op ->
                            op.isEnabled = if (checked) {
                                (op.id != sePair.optionsId)
                            } else true
                        }
                    }
                }
            }
            notifyDataSetChanged()
        }
    }
}
