package com.example.smartdoctor.ui.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.smartdoctor.databinding.ActivityChatDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityChatDetailBinding
    var username:String = " "
    var chatId:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        username = intent.getStringExtra("username").toString()
        chatId = intent.getIntExtra("chatId",0)


        binding.apply {
            usernameTxt.text = username
        }



    }
}