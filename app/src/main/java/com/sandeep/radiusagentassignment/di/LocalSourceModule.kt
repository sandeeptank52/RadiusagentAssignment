package com.sandeep.radiusagentassignment.di

import com.sandeep.radiusagentassignment.data.local.DataSource
import com.sandeep.radiusagentassignment.data.local.LocalDataSource
import com.sandeep.radiusagentassignment.data.models.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration


@Module
@InstallIn(SingletonComponent::class)
class LocalSourceModule {

    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.create(
            schema = setOf(
                RFacilityResponse::class,
                RExclusion::class,
                RFacility::class,
                RFacilityOption::class,
                RDataUpdateTime::class,
            )
        )
        return Realm.open(config)
    }

    @Provides
    fun provideLocalDataSource(realm: Realm): DataSource {
        return LocalDataSource(realm)
    }
}
