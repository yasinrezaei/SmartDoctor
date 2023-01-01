package com.example.smartdoctor.viewmodel.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.UserModel
import com.example.smartdoctor.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val accountRepository: AccountRepository) : ViewModel() {
    val user = MutableLiveData<UserModel>()
    val error = MutableLiveData<String>()
    fun userSignUp(userModel: UserModel){
            viewModelScope.launch {
               accountRepository.userSignUp(userModel).collect(){

                   if(it.isSuccessful){
                       user.postValue(it.body())
                   }
                   else{
                       if(it.code()==400){
                           error.postValue("این نام کاربری قبلا ثبت شده!")
                       }
                       else{
                           error.postValue("حساب ساخته نشد!")
                       }
                   }

               }
            }
    }
}