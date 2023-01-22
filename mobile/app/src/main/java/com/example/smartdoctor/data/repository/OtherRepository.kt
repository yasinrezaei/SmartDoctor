package com.example.smartdoctor.data.repository

import com.example.smartdoctor.data.model.CityListModel
import com.example.smartdoctor.data.server.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class OtherRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllCities():Flow<Response<CityListModel>>{
        return flow {
            emit(apiService.getCitiesList())
        }.flowOn(Dispatchers.IO)
    }


}