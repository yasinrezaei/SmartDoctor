package com.example.smartdoctor.data.model


import com.google.gson.annotations.SerializedName

class CityListModel : ArrayList<CityListModel.CityItem>(){
    data class CityItem(
        @SerializedName("city_name")
        val cityName: String,
        @SerializedName("id")
        val id: Int
    )
}