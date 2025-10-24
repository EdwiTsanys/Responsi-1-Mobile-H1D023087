package com.unsoed.responsi1mobileh1d023087.data.model

import com.google.gson.annotations.SerializedName

data class Coach(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("dateOfBirth")
    val dateOfBirth: String,

    @SerializedName("nationality")
    val nationality: String
)