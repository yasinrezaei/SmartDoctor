package com.example.smartdoctor.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.TokenModel
import com.example.smartdoctor.data.model.UserModel
import com.example.smartdoctor.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val accountRepository: AccountRepository) : ViewModel(){
    val token = MutableLiveData<TokenModel>()
    val error = MutableLiveData<String>()
    fun userLogin(userModel: UserModel){
        viewModelScope.launch {
            accountRepository.userLogin(userModel).collect{
                if(it.isSuccessful){
                    token.postValue(it.body())
                }
                else{
                    error.postValue("خطا!")
                }
            }
        }
    }
}