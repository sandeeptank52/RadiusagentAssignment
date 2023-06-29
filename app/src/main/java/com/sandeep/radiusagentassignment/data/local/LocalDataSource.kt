package com.sandeep.radiusagentassignment.data.local

import com.sandeep.radiusagentassignment.data.models.*
import io.realm.kotlin.Realm
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val realm: Realm) : DataSource {
    override suspend fun fetchData(): FacilityResponse? {
        val responseData: RFacilityResponse? = realm.query(RFacilityResponse::class).first().find()
        return responseData?.toDataClass()
    }

    override suspend fun updateData(data: FacilityResponse) {
        realm.write {
            this.copyToRealm(data.toRealm())
        }
    }

    override suspend fun getLastUpdatedOn(): Long {
        val lastUpdatedOn: RDataUpdateTime? = realm.query(RDataUpdateTime::class).first().find()
        return lastUpdatedOn?.lastUpdateOn ?: 0
    }

    override suspend fun setLastUpdatedOn(time: Long) {
        realm.write {
            this.copyToRealm(RDataUpdateTime().also {
                it.lastUpdateOn = time
            })
        }
    }
}