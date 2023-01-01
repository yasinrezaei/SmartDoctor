package com.example.smartdoctor.data.model


import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    val username: String,
    val password:String
)