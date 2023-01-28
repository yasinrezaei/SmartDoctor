package com.example.smartdoctor.data.model


import com.google.gson.annotations.SerializedName

class ChatsListModel : ArrayList<ChatsListModel.chatItem>(){
    data class chatItem(
        @SerializedName("doctor_id")
        val doctorId: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("user_id")
        val userId: String
    )
}