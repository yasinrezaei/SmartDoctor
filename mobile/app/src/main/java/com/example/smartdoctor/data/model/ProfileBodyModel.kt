package com.example.smartdoctor.data.model


import com.google.gson.annotations.SerializedName

data class ProfileBodyModel(
    @SerializedName("address")
    val address: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("city")
    val city: Int,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("gmc_number")
    val gmcNumber: String,
    @SerializedName("national_code")
    val nationalCode: String
)