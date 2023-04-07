package com.example.teslanews.data.api

import com.example.teslanews.data.model.AppleNewsResponse
import com.example.teslanews.data.model.BusinessNewsResponse
import com.example.teslanews.data.model.TechNewsResponse
import com.example.teslanews.data.model.TeslaNewsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val API_KEY = "55c0328f6c5945a9aa252689d2c89344"


interface ApiService {

    /** Business News */
    @GET("top-headlines?country=us&category=business&apiKey=$API_KEY")
    suspend fun getBusinessNews(): Response<BusinessNewsResponse>

    /** Tech News */
    @GET("top-headlines?sources=techcrunch&apiKey=$API_KEY")
    suspend fun getTechNews(): Response<TechNewsResponse>

    /** Apple News */
    @GET("everything?q=apple&sortBy=popularity&apiKey=$API_KEY")
    suspend fun getAppleNews() : Response<AppleNewsResponse>

    /** Tesla News */
    @GET("everything?q=tesla&sortBy=publishedAt&apiKey=$API_KEY")
    suspend fun getTeslaNews(): Response<TeslaNewsResponse>




















    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"

        var apiService: ApiService? = null

        fun getInstance(): ApiService {
            if (apiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}