package com.sandeep.radiusagentassignment.di

import android.app.Application
import com.sandeep.radiusagentassignment.MyApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApp(): Application {
        return MyApp.getInstance()
    }
}