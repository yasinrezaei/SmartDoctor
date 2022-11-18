package com.example.smartdoctor.ui.account

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.smartdoctor.databinding.ActivityLoginBinding
import com.example.smartdoctor.databinding.ActivityMainBinding
import com.example.smartdoctor.ui.MainActivity

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            loginButton.setOnClickListener {
                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(intent)
            }
            signupBtn.setOnClickListener {
                val intent = Intent(this@LoginActivity,SignupActivity::class.java)
                startActivity(intent)
            }
        }
    }
}