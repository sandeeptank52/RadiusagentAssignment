package com.sandeep.radiusagentassignment.ui

import com.sandeep.radiusagentassignment.data.models.FacilityOption

interface FacilityOptionCallback {
    fun onFacilityOptionClick(option: FacilityOption, isChecked: Boolean)
}