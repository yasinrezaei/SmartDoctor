package com.example.smartdoctor.viewmodel.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.CityListModel
import com.example.smartdoctor.data.model.ProfileBodyModel
import com.example.smartdoctor.data.model.ProfileModel
import com.example.smartdoctor.data.repository.AccountRepository
import com.example.smartdoctor.data.repository.OtherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(private val accountRepository: AccountRepository,private val otherRepository: OtherRepository) : ViewModel(){
    val editedProfile = MutableLiveData<ProfileModel>()
    val citiesList = MutableLiveData<CityListModel>()

    fun editUserProfile(token:String,profileId:Int,profileBodyModel: ProfileBodyModel){
        viewModelScope.launch {

            accountRepository.editUserProfile(token,profileId,profileBodyModel)
                .collect{
                editedProfile.postValue(it.body())
            }

        }
    }

    fun loadAllCitiesList(){
        viewModelScope.launch {
            otherRepository.getAllCities().collect{
                citiesList.postValue(it.body())
            }
        }
    }
}