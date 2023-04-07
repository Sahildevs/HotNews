package com.example.teslanews.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teslanews.data.model.AppleNewsResponse
import com.example.teslanews.data.model.BusinessNewsResponse
import com.example.teslanews.data.model.TechNewsResponse
import com.example.teslanews.data.model.TeslaNewsResponse
import com.example.teslanews.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {


    /** Business news data */
    var businessCoverImg: String? = null
    var businessSource: String? = null
    var businessDescription: String? = null

    /** Tech news data */
    var techCoverImg: String? = null
    var techSource: String? = null
    var techDescription: String? = null

    /** Apple news data */
    var appleCoverImg: String? = null
    var appleSource: String? = null
    var appleDescription: String? = null

    /** Tesla news data */
    var teslaCoverImg: String? = null
    var teslaSource: String? = null
    var teslaDescription: String? = null

    var backButtonPressed = 0



    private var _responseBusinessNews = MutableLiveData<Response<BusinessNewsResponse>>()
    val responseBusinessNews: LiveData<Response<BusinessNewsResponse>> get() = _responseBusinessNews

    private var _responseTechNews = MutableLiveData<Response<TechNewsResponse>>()
    val responseTechNews: LiveData<Response<TechNewsResponse>> get() = _responseTechNews

    private var _responseAppleNews = MutableLiveData<Response<AppleNewsResponse>>()
    val responseAppleNews: LiveData<Response<AppleNewsResponse>> get() = _responseAppleNews

    private var _responseTeslaNews = MutableLiveData<Response<TeslaNewsResponse>>()
    val responseTeslaNews: LiveData<Response<TeslaNewsResponse>> get() = _responseTeslaNews



    fun loadDetailedGeneralNews(image: String, source: String, desc: String) {
        this.businessCoverImg = image
        this.businessSource = source
        this.businessDescription = desc
    }

    fun loadDetailedTechNews(image: String, source: String, desc: String) {
        this.techCoverImg = image
        this.techSource = source
        this.techDescription = desc
    }

    fun loadDetailedAppleNews(image: String, source: String, desc: String) {
        this.appleCoverImg = image
        this.appleSource = source
        this.appleDescription = desc
    }

    fun loadDetailedTeslaNews(image: String, source: String, desc: String) {
        this.teslaCoverImg = image
        this.teslaSource = source
        this.teslaDescription = desc
    }










    /** Business news service call */
    suspend fun getBusinessNews() {
        val res = repository.getBusinessNews()
        _responseBusinessNews.postValue(res)
    }

    /** Tech news service call */
    suspend fun getTechNews() {
        val res = repository.getTechNews()
        _responseTechNews.postValue(res)
    }

    /** Apple news service call */
    suspend fun getAppleNews() {
        val res = repository.getAppleNews()
        _responseAppleNews.postValue(res)
    }

    /** Tesla news service call */
    suspend fun getTeslaNews() {
        val res = repository.getTeslaNews()
        _responseTeslaNews.postValue(res)
    }


}