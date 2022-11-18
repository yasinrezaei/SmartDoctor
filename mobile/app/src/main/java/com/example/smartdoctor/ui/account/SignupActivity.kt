package com.example.smartdoctor.ui.account

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.smartdoctor.databinding.ActivitySignupBinding
import com.example.smartdoctor.ui.MainActivity

class SignupActivity  : AppCompatActivity(){
    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            signupBtn.setOnClickListener {
                val intent = Intent(this@SignupActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}