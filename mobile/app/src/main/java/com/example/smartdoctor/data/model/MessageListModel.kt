package com.example.smartdoctor.data.model


import com.google.gson.annotations.SerializedName

class MessageListModel : ArrayList<MessageListModel.MessageItem>(){
    data class MessageItem(
        @SerializedName("chat")
        val chat: Int,
        @SerializedName("date")
        val date: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("sender_id")
        val senderId: Int,
        @SerializedName("text")
        val text: String
    )
}