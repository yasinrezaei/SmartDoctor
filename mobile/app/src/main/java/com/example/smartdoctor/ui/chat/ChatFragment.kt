package com.example.smartdoctor.ui.chat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartdoctor.R
import com.example.smartdoctor.databinding.FragmentChatBinding
import com.example.smartdoctor.ui.medical_test.HelpDialogFragment
import com.example.smartdoctor.utils.CheckConnection
import com.example.smartdoctor.utils.SaveData
import com.example.smartdoctor.viewmodel.chat.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ChatFragment : Fragment() {

    lateinit var binding:FragmentChatBinding

    @Inject
    lateinit var chatAdapter: ChatAdapter

    private val viewModel:ChatViewModel by viewModels()

    private lateinit var saveData: SaveData

    @Inject
    lateinit var connection: CheckConnection


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

        //check connection and send request
        checkConnectionAndSendRequest()

        //observe on chats list
        viewModel.chatsList.observe(viewLifecycleOwner){
            chatAdapter.differ.submitList(it)
        }

        //observe on error or empty
        viewModel.errorOrEmpty.observe(viewLifecycleOwner){
            Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
        }


        //setup views
        binding.apply {
            help.setOnClickListener {
                HelpDialogFragment(getString(R.string.help_text_chat)).show(parentFragmentManager, HelpDialogFragment(getString(R.string.help_text_chat)).tag)
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

    private fun checkConnectionAndSendRequest(){

        binding.apply {
            help.visibility = View.GONE
            headerTxt.visibility = View.GONE
            chatRecycler.visibility = View.GONE
            connectionConstraint.visibility = View.VISIBLE
        }

        connection.observe(viewLifecycleOwner){
            if(it){
                binding.apply {
                    help.visibility = View.VISIBLE
                    headerTxt.visibility = View.VISIBLE
                    chatRecycler.visibility = View.VISIBLE
                    connectionConstraint.visibility = View.GONE
                }

                viewLifecycleOwner.lifecycleScope.launch{
                    saveData.getToken.collect{ token ->
                        saveData.getProfileId.collect{ profileId ->
                            viewModel.loadChatsList("token $token" , profileId!!)
                        }
                    }
                }

            }
            else{
                binding.apply {
                    help.visibility = View.GONE
                    headerTxt.visibility = View.GONE
                    chatRecycler.visibility = View.GONE
                    connectionConstraint.visibility = View.VISIBLE
                }
            }
        }
    }




}