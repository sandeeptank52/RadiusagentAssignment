package com.sandeep.radiusagentassignment.domain.repo

import com.sandeep.radiusagentassignment.data.models.FacilityResponse
import retrofit2.Response

interface FacilitiesRepository {

    suspend fun getFacilities(): Response<FacilityResponse>
}