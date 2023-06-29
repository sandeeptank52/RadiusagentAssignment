package com.sandeep.radiusagentassignment.data.local

import com.sandeep.radiusagentassignment.data.models.FacilityResponse

interface DataSource {
    suspend fun fetchData(): FacilityResponse?
    suspend fun updateData(data: FacilityResponse)
    suspend fun getLastUpdatedOn(): Long
    suspend fun setLastUpdatedOn(time:Long)
}