package com.sandeep.radiusagentassignment.data.models

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.realm.kotlin.ext.toRealmList
import org.json.JSONArray

fun FacilityResponse.toRealm(): RFacilityResponse {
    val rModel = RFacilityResponse()
    rModel.facilities = this.facilities.map {
        it.toRealm()
    }.toRealmList()
    val gson = Gson()
    val jsonString = gson.toJson(this.exclusions)
    rModel.exclusions = jsonString
    return rModel
}

fun RFacilityResponse.toDataClass(): FacilityResponse {
    val gson = Gson()
    val listType = object : TypeToken<List<List<Exclusion>>>() {}.type
    val exclusion: List<List<Exclusion>> = gson.fromJson(this.exclusions, listType)
    return FacilityResponse(
        this.facilities.map { it.toDataClass() }.toList(),
        exclusion,
    )
}

private fun List<List<Exclusion>>.toJson(): String {
    val jsonArray = JSONArray()
    for (exclusionsList in this) {
        val innerJsonArray = JSONArray()
        for (exclusion in exclusionsList) {
            innerJsonArray.put(exclusion)
        }
        jsonArray.put(innerJsonArray)
    }
    return jsonArray.toString()
}

fun Exclusion.toRealm(): RExclusion {
    return RExclusion().also {
        it.facilityId = this.facilityId
        it.optionsId = this.optionsId
    }

}

fun RExclusion.toDataClass(): Exclusion {
    return Exclusion(
        this.facilityId,
        this.optionsId,
    )
}

fun Facility.toRealm(): RFacility {

    return RFacility().also {
        it.facilityId = this.facilityId
        it.name = this.name
        it.options = this.options.map { option ->
            option.toRealm()
        }.toRealmList()
    }
}

fun RFacility.toDataClass(): Facility {

    return Facility(
        this.facilityId,
        this.name,
        this.options.map {
            it.toDataClass()
        }
    )
}

fun FacilityOption.toRealm(): RFacilityOption {
    return RFacilityOption().also {
        it.id = this.id
        it.icon = this.icon
        it.name = this.name
    }
}

fun RFacilityOption.toDataClass(): FacilityOption {
    return FacilityOption(
        this.name,
        this.icon,
        this.id
    )
}