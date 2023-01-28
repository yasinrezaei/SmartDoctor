package com.example.smartdoctor.data.model


import com.google.gson.annotations.SerializedName

data class MedicalTestDetailResponseModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("test_id")
    val testId: Int,
    @SerializedName("test_response")
    val testResponse: String
)