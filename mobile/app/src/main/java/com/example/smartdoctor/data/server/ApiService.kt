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
    @POST("create-message/")
    suspend fun createMessage(@Header("Authorization") token:String,@Body messageBodyModel: MessageBodyModel) : Response<MessageListModel.MessageItem>


    @POST("create-medical-test")
    suspend fun createMedicalTest(@Header("Authorization") token:String,@Body medicalTestBodyModel: MedicalTestBodyModel) : Response<MedicalTestListModel.MedicalTestItem>

    @GET("get-response")
    suspend fun getResponse(@Header("Authorization") token:String,@Query("test_id") test_id: Int)


    @PUT("edit-user-profile/{id}")
    suspend fun editUserProfile(@Header("Authorization") token:String,@Path("id") profileId:Int ,@Body profileBodyModel: ProfileBodyModel) : Response<ProfileModel>

    @GET("city-list/")
    suspend fun getCitiesList():Response<CityListModel>

    @GET("user-detail/")
    suspend fun getUserId(@Header("Authorization") token:String,@Query("username") username: String):Response<UserModel>

    @GET("user-medical-tests-list/")
    suspend fun getUserMedicalTests(@Header("Authorization") token:String,@Query("profile_id") profileId: Int) : Response<MedicalTestListModel>


    @GET("medical-test-response-detail/")
    suspend fun getMedicalTestResponseDetail(@Header("Authorization") token:String,@Query("test_id") testId: Int) : Response<MedicalTestDetailResponseModel>


}