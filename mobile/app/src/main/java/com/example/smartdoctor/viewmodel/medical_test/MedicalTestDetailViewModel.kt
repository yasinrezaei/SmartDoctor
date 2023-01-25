package com.example.smartdoctor.viewmodel.medical_test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.*
import com.example.smartdoctor.data.repository.MedicalTestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicalTestDetailViewModel @Inject constructor(private val repository: MedicalTestRepository) : ViewModel(){
    var medicalTestResponse = MutableLiveData<MedicalTestDetailResponseModel>()
    var allUserProfileList = MutableLiveData<UserProfileListModel>()
    var newChat = MutableLiveData<ChatsListModel.chatItem>()
    suspend fun getMedicalTestResponseDetail(token:String,testId:Int) = viewModelScope.launch (Dispatchers.IO){
        repository.getMedicalTestResponseDetail(token,testId).collect{
            medicalTestResponse.postValue(it.body())
        }
    }

    suspend fun getAllUserProfile(token: String) = viewModelScope.launch (Dispatchers.IO){
        repository.getAllUserProfiles(token).collect{
            allUserProfileList.postValue(it.body())
        }
    }

    suspend fun createChat(token: String,chatBodyModel: ChatBodyModel) = viewModelScope.launch(Dispatchers.IO){
        repository.createChat(token,chatBodyModel).collect{
            newChat.postValue(it.body())
        }
    }



}