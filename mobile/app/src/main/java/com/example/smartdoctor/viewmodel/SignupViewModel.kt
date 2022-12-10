package com.example.smartdoctor.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.UserModel
import com.example.smartdoctor.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val accountRepository: AccountRepository) : ViewModel() {
    val user = MutableLiveData<UserModel>()
    fun userSignUp(userModel: UserModel){
            viewModelScope.launch {
               accountRepository.userSignUp(userModel).collect(){
                    user.postValue(it.body())
               }

            }
    }
}