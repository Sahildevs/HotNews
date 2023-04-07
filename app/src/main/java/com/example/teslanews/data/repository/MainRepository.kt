package com.example.teslanews.data.repository

import com.example.teslanews.data.api.ApiService
import javax.inject.Inject


class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getBusinessNews() = apiService.getBusinessNews()

    suspend fun getTechNews() = apiService.getTechNews()

    suspend fun getAppleNews() = apiService.getAppleNews()

    suspend fun getTeslaNews() = apiService.getTeslaNews()


}