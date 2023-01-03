package com.example.smartdoctor.data.model


import com.google.gson.annotations.SerializedName

data class DoctorBookingSettingsModel(
    @SerializedName("doctor")
    val doctor: Int,
    @SerializedName("doctor_full_name")
    val doctorFullName: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("max_booking_per_day")
    val maxBookingPerDay: Int,
    @SerializedName("period_of_each_booking")
    val periodOfEachBooking: String,
    @SerializedName("start_time")
    val startTime: String
)