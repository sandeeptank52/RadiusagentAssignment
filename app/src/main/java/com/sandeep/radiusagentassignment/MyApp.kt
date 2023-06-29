package com.sandeep.radiusagentassignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm

@HiltAndroidApp
class MyApp : Application() {

    companion object {
        private var mInstance: MyApp? = null

        @Synchronized
        fun getInstance(): MyApp {
            return mInstance!!
        }
    }

}