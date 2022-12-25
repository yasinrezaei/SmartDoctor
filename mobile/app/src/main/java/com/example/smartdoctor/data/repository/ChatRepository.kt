package com.example.smartdoctor.data.repository

import com.example.smartdoctor.data.model.ChatsListModel
import com.example.smartdoctor.data.model.MessageListModel
import com.example.smartdoctor.data.server.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class ChatRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getUserAllChats(token: String, profileId: Int): Flow<Response<ChatsListModel>> {
        return flow {
            emit(apiService.getUserAllChats(token, profileId))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getChatAllMessages(token: String, chatId: Int): Flow<Response<MessageListModel>> {
        return flow {
            emit(apiService.getChatMessages(token,chatId))
        }.flowOn(Dispatchers.IO)
    }

}