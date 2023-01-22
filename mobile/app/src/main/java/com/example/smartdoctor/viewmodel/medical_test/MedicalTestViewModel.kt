package com.example.smartdoctor.viewmodel.medical_test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.ChatsListModel
import com.example.smartdoctor.data.model.MedicalTestListModel
import com.example.smartdoctor.data.repository.MedicalTestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicalTestViewModel @Inject constructor(private val repository: MedicalTestRepository): ViewModel(){
    val medicalTestsList = MutableLiveData<MedicalTestListModel>()
    val errorOrEmpty = MutableLiveData<String>()

    fun loadMedicalTests(token:String,profileId:Int) = viewModelScope.launch(Dispatchers.IO){
        repository.getUserMedicalTests(token,profileId).collect{
            if(it.isSuccessful){
                medicalTestsList.postValue(it.body())
            }
            else if(it.body()!!.isEmpty()){
                errorOrEmpty.postValue("شما هیچ تستی نداشته اید!")
            }
            else{
                errorOrEmpty.postValue("خطا در دریافت تست ها!")
            }
        }
    }

}