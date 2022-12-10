package com.example.smartdoctor.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.smartdoctor.R
import com.example.smartdoctor.databinding.ActivitySplashBinding
import com.example.smartdoctor.ui.account.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity :AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val fadein_anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        binding.apply {
            logoText.startAnimation(fadein_anim)
        }

        CoroutineScope(Dispatchers.IO).launch {
            delay(2500)
            val intent = Intent(this@SplashActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}