package com.example.smartdoctor.ui.account.signup

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.smartdoctor.R
import com.example.smartdoctor.data.model.UserModel
import com.example.smartdoctor.data.repository.AccountRepository
import com.example.smartdoctor.databinding.ActivitySignupBinding
import com.example.smartdoctor.databinding.FragmentNormalUserSignupBinding
import com.example.smartdoctor.ui.MainActivity
import com.example.smartdoctor.viewmodel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onErrorReturn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    private val viewModel: SignupViewModel by viewModels()


    @Inject
    lateinit var accountRepository: AccountRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            signUpBtn.setOnClickListener {

                var password1 = edtPassword1.text.toString()
                var password2 = edtPassword2.text.toString()

                if (checkPassword(password1, password2)) {
                    //show loading
                    progressBar.visibility = View.VISIBLE
                    //create account
                    var user = UserModel(0, edtUserName.text.toString(), edtPassword1.text.toString())
                    viewModel.userSignUp(user)
                    viewModel.user.observe(this@SignupActivity){
                    Toast.makeText(this@SignupActivity,"حساب کاربری شما با نام کاربری "+it.username+" ساخته شد.",Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE
                    finish()
                    }



                }


            }


        }
    }

    private fun checkPassword(pass1: String, pass2: String): Boolean {
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

    private fun checkSamePassword(pass1: String, pass2: String): Boolean {
        return pass1.equals(pass2)
    }

    private fun checkPasswordLength(password: String): Boolean {
        if (password.length < 5) {
            return false
        }
        return true
    }
}



