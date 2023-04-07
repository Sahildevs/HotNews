package com.example.teslanews.data.model

import com.google.gson.annotations.SerializedName

data class BusinessNewsResponse(

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("articles")
    var news_data : ArrayList<BusinessNewsResponseItem> = arrayListOf()
)

