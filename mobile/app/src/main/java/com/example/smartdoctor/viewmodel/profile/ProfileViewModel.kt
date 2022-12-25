package com.example.smartdoctor.viewmodel.profile

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.ProfileModel
import com.example.smartdoctor.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val accountRepository: AccountRepository) : ViewModel() {
    val profile = MutableLiveData<ProfileModel>()



    fun getUserProfile(token:String,userId:Int){
        viewModelScope.launch {
            accountRepository.getUserProfile(token,userId).collect{
                profile.postValue(it.body())
            }
        }
    }



}