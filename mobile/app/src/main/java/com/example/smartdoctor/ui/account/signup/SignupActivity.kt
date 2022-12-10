package com.example.smartdoctor.ui.account.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.smartdoctor.R
import com.example.smartdoctor.databinding.ActivitySignupBinding
import com.example.smartdoctor.ui.MainActivity

class SignupActivity  : AppCompatActivity(){
    lateinit var binding: ActivitySignupBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            navController = findNavController(R.id.signUpNavHost)
            bottomNav.setupWithNavController(navController)
        }
    }

    override fun onNavigateUp(): Boolean {
        return  navController.navigateUp() || super.onNavigateUp()
    }
}