package com.example.smartdoctor.data.model


import com.google.gson.annotations.SerializedName

class MedicalTestListModel : ArrayList<MedicalTestListModel.MedicalTestItem>(){
    data class MedicalTestItem(
        @SerializedName("id")
        val id: Int,
        @SerializedName("test_input")
        val testInput: String,
        @SerializedName("date")
        val date: String,
        @SerializedName("user_id")
        val userId: Int

    )
}