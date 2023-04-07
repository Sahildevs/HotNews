package com.example.teslanews.data.model

import com.google.gson.annotations.SerializedName

data class TechNewsResponse(

    @SerializedName("articles")
    var tech_news: ArrayList<TechNewsResponseItem> = arrayListOf()
)
