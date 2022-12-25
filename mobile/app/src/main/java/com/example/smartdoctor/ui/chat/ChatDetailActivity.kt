package com.example.smartdoctor.ui.chat

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartdoctor.databinding.ActivityChatDetailBinding
import com.example.smartdoctor.utils.SaveData
import com.example.smartdoctor.viewmodel.chat.ChatDetailViewModel
import com.example.smartdoctor.viewmodel.chat.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ChatDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityChatDetailBinding
    var username:String = " "
    var chatId:Int = 0
    private lateinit var saveData: SaveData



    lateinit var messageAdapter: MessageAdapter


    private val viewModel: ChatDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        saveData = SaveData(this)



        username = intent.getStringExtra("username").toString()
        chatId = intent.getIntExtra("chatId",0)

        lifecycleScope.launch{
            saveData.getToken.collect{
                messageAdapter = MessageAdapter(16)
                viewModel.loadChatMessages("token $it" ,chatId)
            }
        }


        viewModel.messagesList.observe(this){
            messageAdapter.differ.submitList(it)
        }

        binding.recyclerGchat.apply {
            adapter = messageAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        }


        binding.apply {
            usernameTxt.text = username
        }



    }
}