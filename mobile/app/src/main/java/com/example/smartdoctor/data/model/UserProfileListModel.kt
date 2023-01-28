package com.example.smartdoctor.data.model


import com.google.gson.annotations.SerializedName

class UserProfileListModel : ArrayList<UserProfileListModel.UserProfileItem>(){
    data class UserProfileItem(
        @SerializedName("address")
        val address: String,
        @SerializedName("age")
        val age: Int,
        @SerializedName("city")
        val city: Int,
        @SerializedName("city_name")
        val cityName: String,
        @SerializedName("full_name")
        val fullName: String,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("gmc_number")
        val gmcNumber: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("isDoctor")
        val isDoctor: Boolean,
        @SerializedName("medical_expertise")
        val medicalExpertise: String,
        @SerializedName("national_code")
        val nationalCode: String,
        @SerializedName("user_id")
        val userId: String
    )
}