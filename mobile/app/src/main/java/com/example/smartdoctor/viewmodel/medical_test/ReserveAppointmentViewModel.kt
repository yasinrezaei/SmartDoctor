package com.example.smartdoctor.viewmodel.medical_test

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.BookingBodyModel
import com.example.smartdoctor.data.model.BookingListModel
import com.example.smartdoctor.data.model.MedicalTestListModel
import com.example.smartdoctor.data.repository.MedicalTestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReserveAppointmentViewModel  @Inject constructor(private val repository: MedicalTestRepository): ViewModel(){
    val doctorBookingsList = MutableLiveData<BookingListModel>()
    val newBooking = MutableLiveData<BookingListModel.BookingItem>()
    fun loadDoctorBookingsList(token:String,profileId:Int) = viewModelScope.launch (Dispatchers.IO){

        repository.getDoctorAllBookings(token,profileId).collect{
            Log.e("hoom","viewmodel")
            doctorBookingsList.postValue(it.body())
        }
    }

    fun createBooking(token: String,bookingBodyModel: BookingBodyModel) = viewModelScope.launch (Dispatchers.IO){
        repository.createBooking(token,bookingBodyModel).collect{
            newBooking.postValue(it.body())
        }
    }
}