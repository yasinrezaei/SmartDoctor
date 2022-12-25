package com.example.smartdoctor.viewmodel.chat


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.ChatsListModel
import com.example.smartdoctor.data.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val repository: ChatRepository) : ViewModel(){
        val chatsList = MutableLiveData<ChatsListModel>()

        fun loadChatsList(token:String,profileId:Int) = viewModelScope.launch (Dispatchers.IO){
            repository.getUserAllChats(token,profileId).collect{
                chatsList.postValue(it.body())
            }
        }



}