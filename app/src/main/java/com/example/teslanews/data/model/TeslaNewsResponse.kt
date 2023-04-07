package com.example.teslanews.data.model

import com.google.gson.annotations.SerializedName

data class TeslaNewsResponse(

    @SerializedName("articles")
    val tesla_news: ArrayList<TeslaNewsResponseItem> = arrayListOf()

)
