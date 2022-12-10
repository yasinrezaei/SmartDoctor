package com.example.smartdoctor.data.server

import com.example.smartdoctor.data.model.UserModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api-register-user")
    suspend fun userSignUp(@Body user: UserModel) : Response<UserModel>
}