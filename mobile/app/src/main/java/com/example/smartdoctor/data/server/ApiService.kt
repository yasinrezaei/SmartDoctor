package com.example.smartdoctor.data.server

import com.example.smartdoctor.data.model.ChatsListModel
import com.example.smartdoctor.data.model.ProfileModel
import com.example.smartdoctor.data.model.TokenModel
import com.example.smartdoctor.data.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("api-register-user")
    suspend fun userSignUp(@Body user: UserModel) : Response<UserModel>

    @POST("api-token-auth/")
    suspend fun userLogin(@Body user: UserModel) :Response<TokenModel>

    @GET("user-profile/")
    suspend fun getUserProfile(@Header("Authorization") token:String,@Query("user_id") userId: Int): Response<ProfileModel>

    @GET("user-chats-list/")
    suspend fun getUserAllChats(@Header("Authorization") token:String,@Query("profile_id") profileId: Int): Response<ChatsListModel>

}