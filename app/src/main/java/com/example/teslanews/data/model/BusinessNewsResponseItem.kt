package com.example.teslanews.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BusinessNewsResponseItem(
    @SerializedName("source") val source: BusinessNewsSource? = null,
    @SerializedName("author") val author: String? = null,
    @SerializedName("content") val content: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("publishedAt") val publishedAt: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("urlToImage") val urlToImage: String? = null

): Serializable