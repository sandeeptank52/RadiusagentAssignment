package com.sandeep.radiusagentassignment.data.models

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

open class RFacilityResponse : RealmObject {
    var facilities: RealmList<RFacility> = realmListOf()
    var exclusions: String? = null

}

open class RExclusion : RealmObject {
    var facilityId: String? = null
    var optionsId: String? = null
}

open class RFacility : RealmObject {
    var facilityId: String? = null
    var name: String? = null
    var options: RealmList<RFacilityOption> = realmListOf()
}

open class RFacilityOption : RealmObject {
    var name: String? = null
    var icon: String? = null
    var id: String? = null
    var isSelected: Boolean = false
    var isEnabled: Boolean = true
}

open class RDataUpdateTime:RealmObject{
    var lastUpdateOn : Long? = 0
}