package com.example.smartdoctor.ui.chat

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.format.Time
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartdoctor.data.model.MessageBodyModel
import com.example.smartdoctor.data.model.MessageListModel
import com.example.smartdoctor.databinding.ActivityChatDetailBinding
import com.example.smartdoctor.utils.SaveData
import com.example.smartdoctor.viewmodel.chat.ChatDetailViewModel
import com.example.smartdoctor.viewmodel.chat.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class ChatDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityChatDetailBinding
    var username:String = " "
    var chatId:Int = 0
    private lateinit var saveData: SaveData
    var listMessages :ArrayList<MessageListModel.MessageItem>  = arrayListOf()


    lateinit var messageAdapter: MessageAdapter


    private val viewModel: ChatDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        saveData = SaveData(this)


        //repeat load data
        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                lifecycleScope.launch{
                    saveData.getToken.collect{
                        viewModel.loadChatMessages("token $it" ,chatId)
                    }
                }
                mainHandler.postDelayed(this, 5000)
            }
        })




        username = intent.getStringExtra("username").toString()
        chatId = intent.getIntExtra("chatId",0)

        lifecycleScope.launch{
            saveData.getToken.collect{ token ->
                saveData.getProfileId.collect{ profileId ->
                    messageAdapter = MessageAdapter(profileId!!)
                    viewModel.loadChatMessages("token $token" ,chatId)
                }

            }
        }

        //all messages
        viewModel.messagesList.observe(this){
            listMessages.clear()
            for(i in it!!.size - 1 downTo 0 step 1){
                listMessages.add(it!![i])
            }
            setUpRecyclerView(listMessages)

        }

        //new message
        viewModel.newMessage.observe(this){
            listMessages.add(0,it!!) //add to first of list
            setUpRecyclerView(listMessages)
        }




        binding.apply {
            usernameTxt.text = username

            buttonGchatSend.setOnClickListener {
                GlobalScope.launch(Dispatchers.IO) {
                    saveData.getProfileId.collect{ profileId ->
                        var messageBodyModel = MessageBodyModel(chatId,profileId!!,editGchatMessage.text.toString())
                        editGchatMessage.setText("")
                        saveData.getToken.collect{
                            viewModel.sendMessage("token $it",messageBodyModel)
                        }
                    }

                }
            }
        }



    }



    private fun setUpRecyclerView(listMessages :ArrayList<MessageListModel.MessageItem>) {

        messageAdapter.differ.submitList(listMessages)

        binding.recyclerGchat.apply {
            adapter = messageAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,true)
        }
    }




}