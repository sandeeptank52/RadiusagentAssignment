package com.sandeep.radiusagentassignment.data.models

import com.google.gson.annotations.SerializedName


data class FacilityResponse(
    @SerializedName("facilities") var facilities: List<Facility> = arrayListOf(),
    @SerializedName("exclusions") var exclusions: List<List<Exclusion>> = arrayListOf()
)

data class Exclusion(
    @SerializedName("facility_id") var facilityId: String? = null,
    @SerializedName("options_id") var optionsId: String? = null
)

data class Facility(
    @SerializedName("facility_id") var facilityId: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("options") var options: List<FacilityOption> = arrayListOf(),
)

data class FacilityOption(
    @SerializedName("name") var name: String? = null,
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("id") var id: String? = null,
    var isSelected: Boolean = false,
    var isEnabled: Boolean = true
)