package com.sandeep.radiusagentassignment.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeep.radiusagentassignment.data.models.Exclusion
import com.sandeep.radiusagentassignment.data.models.FacilityOption
import com.sandeep.radiusagentassignment.data.models.FacilityResponse
import com.sandeep.radiusagentassignment.domain.repo.FacilitiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: FacilitiesRepository) :
    ViewModel() {

    private val _facilities = MutableLiveData<FacilityResponse>()
    val facilities: LiveData<FacilityResponse> get() = _facilities
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> get() = _errorMsg

    init {
        fetchFacilities()
    }

    private fun fetchFacilities() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val response = repository.getFacilities()
            if (response.isSuccessful) {
                _facilities.postValue(response.body())
            } else {
                _errorMsg.postValue(response.message())
            }
            _isLoading.postValue(false)
        }
    }

}