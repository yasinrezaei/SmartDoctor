package com.example.smartdoctor.viewmodel.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.ChatsListModel
import com.example.smartdoctor.data.model.MessageListModel
import com.example.smartdoctor.data.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(private val repository: ChatRepository) : ViewModel() {
    val messagesList = MutableLiveData<MessageListModel>()

    fun loadChatMessages(token:String,chatId:Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getChatAllMessages(token,chatId).collect{
            messagesList.postValue(it.body())
        }
    }
}