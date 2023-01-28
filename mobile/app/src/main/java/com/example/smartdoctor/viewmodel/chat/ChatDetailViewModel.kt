package com.example.smartdoctor.viewmodel.chat

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.MessageBodyModel
import com.example.smartdoctor.data.model.MessageListModel
import com.example.smartdoctor.data.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(private val repository: ChatRepository) : ViewModel() {

    var messagesList = MutableLiveData<MessageListModel?> ()
    var newMessage = MutableLiveData<MessageListModel.MessageItem?>()

    fun loadChatMessages(token:String,chatId:Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getChatAllMessages(token,chatId).collect{
            messagesList.postValue(it.body())
        }
    }

    fun sendMessage(token:String,messageBodyModel: MessageBodyModel) = viewModelScope.launch (Dispatchers.IO){

        repository.createMessage(token,messageBodyModel).collect{
            //loadChatMessages(token,messageBodyModel.chat)
            newMessage.postValue(it.body())
        }


    }
}