package com.example.teslanews.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TechNewsSource(

    @SerializedName("id") val id: Any,
    @SerializedName("name") val name: String

) : Serializable
