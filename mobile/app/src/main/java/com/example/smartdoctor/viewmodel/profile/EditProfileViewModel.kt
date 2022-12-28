package com.example.smartdoctor.viewmodel.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.ProfileBodyModel
import com.example.smartdoctor.data.model.ProfileModel
import com.example.smartdoctor.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(private val accountRepository: AccountRepository) : ViewModel(){
    val editedProfile = MutableLiveData<ProfileModel>()

    fun editUserProfile(token:String,profileId:Int,profileBodyModel: ProfileBodyModel){
        viewModelScope.launch {
            //error
            Log.e("hoom", "viewmodel" )
            //editedProfile.postValue(ProfileModel("",2,"fff","dffd","fem","3333","232",1,false,"fff"))

            accountRepository.editUserProfile(token,profileId,profileBodyModel)
                .collect{
                editedProfile.postValue(it.body())
            }



        }
    }
}