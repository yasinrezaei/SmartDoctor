package com.example.smartdoctor.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.smartdoctor.data.model.UserModel
import com.example.smartdoctor.databinding.ActivityLoginBinding
import com.example.smartdoctor.ui.MainActivity
import com.example.smartdoctor.ui.account.signup.SignupActivity
import com.example.smartdoctor.utils.CheckConnection
import com.example.smartdoctor.utils.SaveData
import com.example.smartdoctor.viewmodel.account.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var connection: CheckConnection
    private var connectionStatus:Boolean = false





    private lateinit var saveData:SaveData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        saveData  = SaveData(this)




        //checkConnection
        checkConnection()

        binding.apply {
            viewModel.token.observe(this@LoginActivity){
                progressBar.visibility = View.GONE
                Toast.makeText(
                    this@LoginActivity,
                    "ورود با موفقیت انجام شد",
                    Toast.LENGTH_SHORT
                ).show()


               GlobalScope.launch (Dispatchers.IO){
                   saveToken(it.token)
               }


                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(intent)
                finish()







            }
            viewModel.error.observe(this@LoginActivity){
                progressBar.visibility = View.GONE
                Toast.makeText(
                    this@LoginActivity,
                    "خطا در ورود!",
                    Toast.LENGTH_SHORT
                ).show()
            }



            loginBtn.setOnClickListener {
                var user = UserModel(0,editTextTextPersonName.text.toString(),editTextTextPassword.text.toString())
                if(connectionStatus){
                    progressBar.visibility = View.VISIBLE
                    viewModel.userLogin(user)
                }else{
                    Toast.makeText(
                        this@LoginActivity,
                        "ارتباط شما با اینترنت قطع است!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            signUpTxt.setOnClickListener {
                val intent = Intent(this@LoginActivity, SignupActivity::class.java)
                startActivity(intent)
            }

        }
    }


    private fun checkConnection(){
        connection.observe(this){
            connectionStatus = it
        }
    }

    suspend fun saveToken(token:String){
        GlobalScope.launch {
            saveData.saveToDataStore(token,1)
        }
    }

}