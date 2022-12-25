package com.example.smartdoctor.ui.account.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.smartdoctor.data.model.UserModel
import com.example.smartdoctor.databinding.ActivitySignupBinding
import com.example.smartdoctor.utils.CheckConnection
import com.example.smartdoctor.viewmodel.account.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    private val viewModel: SignupViewModel by viewModels()

    @Inject
    lateinit var connection: CheckConnection
    private var connectionStatus:Boolean = false




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //checkConnection
        checkConnection()

        binding.apply {

            //observe on error or success
            viewModel.error.observe(this@SignupActivity) {
                Toast.makeText(
                    this@SignupActivity,
                    it,
                    Toast.LENGTH_SHORT
                ).show()
                //hide loading
                progressBar.visibility = View.GONE
            }
            viewModel.user.observe(this@SignupActivity) {
                Toast.makeText(
                    this@SignupActivity,
                    "حساب کاربری شما با نام کاربری " + it.username + " ساخته شد.",
                    Toast.LENGTH_SHORT
                ).show()
                //hide loading
                progressBar.visibility = View.GONE
                finish()
            }
            //signup
            signUpBtn.setOnClickListener {
                var username = edtUserName.text.toString()
                var password1 = edtPassword1.text.toString()
                var password2 = edtPassword2.text.toString()
                if(connectionStatus){
                    if(checkUsernameLength(username)){
                        if (checkPassword(password1, password2)) {
                            //show loading
                            progressBar.visibility = View.VISIBLE
                            //create account
                            var user =
                                UserModel(0, username, password1)
                            viewModel.userSignUp(user)
                        }
                    }

                }
                else{
                    Toast.makeText(
                        this@SignupActivity,
                        "ارتباط شما با اینترنت قطع است!",
                        Toast.LENGTH_SHORT
                    ).show()
                }








            }


        }
    }

    fun checkPassword(pass1: String, pass2: String): Boolean {
        if (checkSamePassword(pass1, pass2)) {
            if (checkPasswordLength(pass1)) {
                return true
            } else {
                Toast.makeText(
                    this,
                    "طول رمز وارد شده باید بیشتر از 4 حرف باشد!",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        } else {
            Toast.makeText(this@SignupActivity, "رمز های وارد شده یکسان نیست!", Toast.LENGTH_SHORT)
                .show()
            return false
        }
    }

    private fun checkUsernameLength(username: String): Boolean {
        if (username.length < 4) {
            Toast.makeText(this@SignupActivity, "طول نام کاربری باید بیشتر از 4 حرف باشد!", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    private fun checkSamePassword(pass1: String, pass2: String): Boolean {
        return pass1.equals(pass2)
    }

    private fun checkPasswordLength(password: String): Boolean {
        if (password.length < 5) {
            return false
        }
        return true
    }

    private fun checkConnection(){
        connection.observe(this){
            connectionStatus = it
        }

    }
}



