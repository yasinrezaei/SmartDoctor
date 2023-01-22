package com.example.smartdoctor.viewmodel.medical_test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.MedicalTestBodyModel
import com.example.smartdoctor.data.model.MedicalTestListModel
import com.example.smartdoctor.data.model.MessageBodyModel
import com.example.smartdoctor.data.model.MessageListModel
import com.example.smartdoctor.data.repository.ChatRepository
import com.example.smartdoctor.data.repository.MedicalTestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoMedicalTestViewModel @Inject constructor(private val repository: MedicalTestRepository) : ViewModel()  {
    var newMedicalTest = MutableLiveData<MedicalTestListModel.MedicalTestItem?>()
    var getResponse = MutableLiveData<Boolean>()
    fun createMedicalTest(token:String,medicalTestBodyModel: MedicalTestBodyModel) = viewModelScope.launch (
        Dispatchers.IO){
        repository.createMedicalTest(token,medicalTestBodyModel).collect{
            newMedicalTest.postValue(it.body())
        }
    }

    fun getResponse(token: String,testId:Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getResponse(token,testId)
        getResponse.postValue(true)
    }
}