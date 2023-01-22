package com.example.smartdoctor.viewmodel.medical_test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.MedicalTestDetailResponseModel
import com.example.smartdoctor.data.model.MedicalTestListModel
import com.example.smartdoctor.data.repository.MedicalTestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicalTestDetailViewModel @Inject constructor(private val repository: MedicalTestRepository) : ViewModel(){
    var medicalTestResponse = MutableLiveData<MedicalTestDetailResponseModel>()

    suspend fun getMedicalTestResponseDetail(token:String,testId:Int) = viewModelScope.launch (Dispatchers.IO){
        repository.getMedicalTestResponseDetail(token,testId).collect{
            medicalTestResponse.postValue(it.body())
        }
    }



}