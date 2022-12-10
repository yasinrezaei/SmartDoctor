package com.example.smartdoctor.data.repository

import com.example.smartdoctor.data.model.UserModel
import com.example.smartdoctor.data.server.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class AccountRepository @Inject constructor(private val apiService: ApiService){
    suspend fun userSignUp(userModel: UserModel):Flow<Response<UserModel>>{
        return flow {
            emit(apiService.userSignUp(userModel))
        }.flowOn(Dispatchers.IO)
    }
}