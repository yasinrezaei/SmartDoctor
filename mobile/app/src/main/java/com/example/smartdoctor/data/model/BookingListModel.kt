package com.example.smartdoctor.data.model


import com.google.gson.annotations.SerializedName

class BookingListModel : ArrayList<BookingListModel.BookingItem>(){
    data class BookingItem(
        @SerializedName("approved")
        val approved: Boolean,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("date")
        val date: String,
        @SerializedName("doctor")
        val doctor: Int,
        @SerializedName("doctor_full_name")
        val doctorFullName: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("time")
        val time: String,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("user")
        val user: Int,
        @SerializedName("user_full_name")
        val userFullName: String
    )
}