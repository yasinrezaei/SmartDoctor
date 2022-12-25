package com.example.smartdoctor.viewmodel.account

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.TokenModel
import com.example.smartdoctor.data.model.UserModel
import com.example.smartdoctor.data.repository.AccountRepository
import com.example.smartdoctor.ui.MainActivity
import com.example.smartdoctor.utils.SaveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val accountRepository: AccountRepository,@ApplicationContext context: Context) : ViewModel(){
    val token = MutableLiveData<TokenModel>()
    val error = MutableLiveData<String>()
    //var saveData  = SaveData(context)
    fun userLogin(userModel: UserModel){
        viewModelScope.launch {
            accountRepository.userLogin(userModel).collect{
                if(it.isSuccessful){
                   // saveData.saveToDataStore(it.body()?.token ?: "",1)
                    token.postValue(it.body())
                }
                else{
                    error.postValue("خطا!")
                }
            }
        }
    }
}