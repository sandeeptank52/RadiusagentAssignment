package com.sandeep.radiusagentassignment.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.radiusagentassignment.data.models.FacilityOption
import com.sandeep.radiusagentassignment.databinding.ItemFacilityOptionBinding

class FacilityOptionAdapter(
    private val options: List<FacilityOption>,
    private val optionClickCallBack: FacilityOptionCallback
) :
    RecyclerView.Adapter<FacilityOptionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFacilityOptionBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = options[position]
        holder.bind(option)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    inner class ViewHolder(private val binding: ItemFacilityOptionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(option: FacilityOption) {
            binding.option = option
            binding.optionChecked.isEnabled = option.isEnabled
            binding.root.alpha = if (option.isEnabled) 1f else 0.4f
            option.icon?.let { img ->
                binding.optionIcon.setImageResource(
                    getDrawableResId(
                        binding.optionIcon.context,
                        img.replace("-", "_")
                    )
                )
            }
            binding.optionChecked.setOnCheckedChangeListener(null)
            binding.optionChecked.isChecked = if(option.isEnabled) option.isSelected else false
            binding.optionChecked.setOnCheckedChangeListener { _, isChecked ->
                option.isSelected = isChecked
                optionClickCallBack.onFacilityOptionClick(option, isChecked)
            }
            binding.executePendingBindings()
        }

        private fun getDrawableResId(context: Context, drawableName: String): Int {
            return context.resources.getIdentifier(drawableName, "drawable", context.packageName)
        }
    }
}