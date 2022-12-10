package com.example.smartdoctor.ui.chat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartdoctor.data.model.ChatModel
import com.example.smartdoctor.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChatFragment : Fragment() {

    lateinit var binding:FragmentChatBinding

    @Inject
    lateinit var chatAdapter: ChatAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            var chat1 = ChatModel(1,"یاسین رضایی")
            var chat2 = ChatModel(1,"حسین میرحسینی")
            var chat3 = ChatModel(1,"یگانه خواجه پور")
            var chats = listOf(chat1,chat2,chat3)
            chatAdapter.differ.submitList(chats)
            chatAdapter.onItemClick = {
                val intent = Intent(activity,ChatDetailActivity::class.java)
                startActivity(intent)
            }
            chatRecycler.apply {
                adapter = chatAdapter
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            }

        }


    }
}