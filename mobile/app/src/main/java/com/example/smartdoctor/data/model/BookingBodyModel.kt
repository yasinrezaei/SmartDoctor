package com.example.smartdoctor.data.model


import com.google.gson.annotations.SerializedName

data class BookingBodyModel(
    @SerializedName("date")
    val date: String,
    @SerializedName("doctor")
    val doctor: Int,
    @SerializedName("time")
    val time: String,
    @SerializedName("user")
    val user: Int
)