package com.sandeep.radiusagentassignment.data.remote

import com.sandeep.radiusagentassignment.data.models.FacilityResponse
import retrofit2.Response
import retrofit2.http.GET


interface AppApis {

    @GET("ad-assignment/db")
    suspend fun getFacilities(): Response<FacilityResponse>

}