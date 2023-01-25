package com.example.smartdoctor.viewmodel.reserve

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.BookingListModel
import com.example.smartdoctor.data.model.MedicalTestListModel
import com.example.smartdoctor.data.repository.MedicalTestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReserveViewModel @Inject constructor(private val repository: MedicalTestRepository): ViewModel(){
    val bookingList = MutableLiveData<BookingListModel>()
    val errorOrEmpty = MutableLiveData<String>()

    fun loadBookingList(token:String,profileId:Int) = viewModelScope.launch(Dispatchers.IO){
        repository.getUserAllBookings(token,profileId).collect{
            if(it.isSuccessful){
                bookingList.postValue(it.body())
            }
            else if(it.body()!!.isEmpty()){
                errorOrEmpty.postValue("شما هیچ نوبتی نداشته اید!")
            }
            else{
                errorOrEmpty.postValue("خطا در دریافت نوبت ها!")
            }
        }
    }
}