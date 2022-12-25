package com.example.smartdoctor.ui.chat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartdoctor.R
import com.example.smartdoctor.databinding.FragmentChatBinding
import com.example.smartdoctor.ui.medical_test.HelpDialogFragment
import com.example.smartdoctor.utils.SaveData
import com.example.smartdoctor.viewmodel.chat.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ChatFragment : Fragment() {

    lateinit var binding:FragmentChatBinding

    @Inject
    lateinit var chatAdapter: ChatAdapter

    private val viewModel:ChatViewModel by viewModels()

    private lateinit var saveData: SaveData


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater,container,false)
        saveData = SaveData(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewLifecycleOwner.lifecycleScope.launch{
            saveData.getToken.collect{
                viewModel.loadChatsList("token $it" ,16)
            }
        }



        binding.apply {
            help.setOnClickListener {
                HelpDialogFragment(getString(R.string.help_text_chat)).show(parentFragmentManager, HelpDialogFragment(getString(R.string.help_text_chat)).tag)
            }


            viewModel.chatsList.observe(viewLifecycleOwner){
                chatAdapter.differ.submitList(it)
            }

            chatAdapter.onItemClick = {
                val intent = Intent(activity,ChatDetailActivity::class.java)
                intent.putExtra("username",it.doctorId)
                intent.putExtra("chatId",it.id)
                startActivity(intent)
            }
            chatRecycler.apply {
                adapter = chatAdapter
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            }



        }


    }




}