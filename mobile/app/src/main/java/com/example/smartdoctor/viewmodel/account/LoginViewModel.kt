package com.example.smartdoctor.viewmodel.account

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.TokenModel
import com.example.smartdoctor.data.model.UserModel
import com.example.smartdoctor.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val accountRepository: AccountRepository,@ApplicationContext context: Context) : ViewModel(){
    val token = MutableLiveData<TokenModel>()
    val error = MutableLiveData<String>()
    val userId = MutableLiveData<Int>()
    //var saveData  = SaveData(context)
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

    fun getUserId(token:String,username:String){
        viewModelScope.launch {
            accountRepository.getUserId(token,username).collect{
                userId.postValue(it.body()!!.id)
            }
        }
    }
}