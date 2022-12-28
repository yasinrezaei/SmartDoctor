package com.example.smartdoctor.data.repository

import android.util.Log
import com.example.smartdoctor.data.model.ProfileBodyModel
import com.example.smartdoctor.data.model.ProfileModel
import com.example.smartdoctor.data.model.TokenModel
import com.example.smartdoctor.data.model.UserModel
import com.example.smartdoctor.data.server.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class AccountRepository @Inject constructor(private val apiService: ApiService){

    suspend fun getUserProfile(token:String,userId:Int):Flow<Response<ProfileModel>>{
        return flow {
            emit(apiService.getUserProfile(token,userId))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun userSignUp(userModel: UserModel):Flow<Response<UserModel>>{
        return flow {
            emit(apiService.userSignUp(userModel))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun userLogin(userModel: UserModel):Flow<Response<TokenModel>>{
        return flow {
            emit(apiService.userLogin(userModel))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun editUserProfile(token:String,profileId:Int,profileBodyModel: ProfileBodyModel) : Flow<Response<ProfileModel>>{
        Log.e("hoom", "repo" )
        return flow {
            emit(apiService.editUserProfile(token,profileId,profileBodyModel))
        }.flowOn(Dispatchers.IO)
    }
}