package com.example.smartdoctor.ui.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.smartdoctor.databinding.ActivityChatDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatDetailActivity : AppCompatActivity() {
    lateinit var binding:ActivityChatDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}