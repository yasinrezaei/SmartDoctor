package com.example.smartdoctor.data.repository

import com.example.smartdoctor.data.model.*
import com.example.smartdoctor.data.server.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class MedicalTestRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getUserMedicalTests(token:String,profileId: Int) : Flow<Response<MedicalTestListModel>>{
        return flow {
            emit(apiService.getUserMedicalTests(token,profileId))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun createMedicalTest(token: String,medicalTestBodyModel: MedicalTestBodyModel) : Flow<Response<MedicalTestListModel.MedicalTestItem>>{
        return flow {
            emit(apiService.createMedicalTest(token,medicalTestBodyModel))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getResponse(token: String,testId:Int){
        apiService.getResponse(token,testId)
    }

    suspend fun getMedicalTestResponseDetail(token: String,testId:Int):Flow<Response<MedicalTestDetailResponseModel>>{
        return flow<Response<MedicalTestDetailResponseModel>> {
            emit(apiService.getMedicalTestResponseDetail(token,testId))
        }.flowOn(Dispatchers.IO)
    }


    suspend fun getAllUserProfiles(token: String) : Flow<Response<UserProfileListModel>> {
        return flow<Response<UserProfileListModel>> {
            emit(apiService.getAllUserProfile(token))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDoctorAllBookings(token: String,profileId:Int) : Flow<Response<BookingListModel>>{
        return flow{
            emit(apiService.getDoctorAllBookings(token,profileId))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun createBooking(token: String,bookingBodyModel: BookingBodyModel) : Flow<Response<BookingListModel.BookingItem>>{
        return flow {
            emit(apiService.createBooking(token,bookingBodyModel))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUserAllBookings(token: String,profileId:Int) : Flow<Response<BookingListModel>>{
        return flow {
            emit(apiService.getUserAllBookings(token,profileId))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun createChat(token: String,chatBodyModel: ChatBodyModel) : Flow<Response<ChatsListModel.chatItem>>{
        return flow {
            emit(apiService.createChat(token,chatBodyModel))
        }.flowOn(Dispatchers.IO)
    }
}