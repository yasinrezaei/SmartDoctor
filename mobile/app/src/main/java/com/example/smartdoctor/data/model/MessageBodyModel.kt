package com.example.smartdoctor.data.model


import com.google.gson.annotations.SerializedName

data class MessageBodyModel(
    @SerializedName("chat")
    val chat: Int,
    @SerializedName("sender_id")
    val senderId: Int,
    @SerializedName("text")
    val text: String
)