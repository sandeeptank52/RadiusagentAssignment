package com.sandeep.radiusagentassignment.data.remote.repo

import com.sandeep.radiusagentassignment.data.local.LocalDataSource
import com.sandeep.radiusagentassignment.data.models.FacilityResponse
import com.sandeep.radiusagentassignment.data.remote.AppApis
import com.sandeep.radiusagentassignment.domain.repo.FacilitiesRepository
import retrofit2.Response
import javax.inject.Inject

class FacilitiesRepositoryImpl @Inject constructor(
    private val appApi: AppApis,
    private val localDataSource: LocalDataSource
) :
    FacilitiesRepository {
    override suspend fun getFacilities(): Response<FacilityResponse> {
        val updateInterval: Long = 24 * 60 * 60 * 1000
        val localData = localDataSource.fetchData()
        val currentTime = System.currentTimeMillis()
        val lastUpdatedOn = localDataSource.getLastUpdatedOn()
        return if (localData == null || (currentTime - lastUpdatedOn) > updateInterval) {
            val newData = appApi.getFacilities()
            saveDataInLocal(newData)
            newData
        } else {
            Response.success(localData)
        }
    }

    private suspend fun saveDataInLocal(newData: Response<FacilityResponse>) {
        newData.body()?.let {
            localDataSource.updateData(it)
            localDataSource.setLastUpdatedOn(System.currentTimeMillis())
        }
    }
}