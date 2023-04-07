package com.example.teslanews.di

import android.content.Context
import com.example.teslanews.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesApiService(@ApplicationContext context: Context): ApiService{
        return ApiService.getInstance()
    }
}