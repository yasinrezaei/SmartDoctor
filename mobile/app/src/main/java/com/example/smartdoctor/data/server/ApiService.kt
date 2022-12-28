package com.example.smartdoctor.data.server

import androidx.room.PrimaryKey
import com.example.smartdoctor.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("api-register-user")
    suspend fun userSignUp(@Body user: UserModel) : Response<UserModel>

    @POST("api-token-auth/")
    suspend fun userLogin(@Body user: UserModel) :Response<TokenModel>

    @GET("user-profile/")
    suspend fun getUserProfile(@Header("Authorization") token:String,@Query("user_id") userId: Int): Response<ProfileModel>

    @GET("user-chats-list/")
    suspend fun getUserAllChats(@Header("Authorization") token:String,@Query("profile_id") profileId: Int): Response<ChatsListModel>
    @GET("chat-messages/")
    suspend fun getChatMessages(@Header("Authorization") token:String,@Query("chat_id") chatId: Int) : Response<MessageListModel>


    @PUT("edit-user-profile/{id}")
    suspend fun editUserProfile(@Header("Authorization") token:String,@Path("id") profileId:Int ,@Body profileBodyModel: ProfileBodyModel) : Response<ProfileModel>

}