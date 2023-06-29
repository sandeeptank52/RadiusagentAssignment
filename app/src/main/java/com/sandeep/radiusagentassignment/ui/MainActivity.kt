package com.sandeep.radiusagentassignment.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sandeep.radiusagentassignment.data.models.FacilityOption
import com.sandeep.radiusagentassignment.databinding.ActivityMainBinding
import com.sandeep.radiusagentassignment.ui.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val vm: MainViewModel by viewModels()
    private lateinit var facilityAdapter: FacilityAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()
        initUI()
    }

    private fun initUI() {
    }

    private fun observeViewModel() {
        vm.facilities.observe(this) {
            binding.recyclerView.adapter = FacilityAdapter(it.facilities,it.exclusions)
        }
    }


}