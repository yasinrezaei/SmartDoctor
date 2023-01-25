package com.example.smartdoctor.data.model


import com.google.gson.annotations.SerializedName

data class ChatBodyModel(
    @SerializedName("doctor_id")
    val doctorId: Int,
    @SerializedName("user_id")
    val userId: Int
)