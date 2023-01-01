package com.example.smartdoctor.data.model


import com.google.gson.annotations.SerializedName

data class ProfileModel(
    @SerializedName("address")
    val address: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("city_name")
    val city: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("gmc_number")
    val gmcNumber: String,
    @SerializedName("national_code")
    val nationalCode: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isDoctor")
    val isDoctor: Boolean,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("city")
    val cityId: Int
)