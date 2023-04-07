package com.example.teslanews.data.model

import com.google.gson.annotations.SerializedName

data class AppleNewsResponse(

    @SerializedName("articles")
    val apple_news: ArrayList<AppleNewsResponseItem> = arrayListOf()

)
