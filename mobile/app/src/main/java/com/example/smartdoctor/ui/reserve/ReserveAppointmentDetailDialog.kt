package com.example.smartdoctor.ui.reserve

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.smartdoctor.data.model.ChatBodyModel
import com.example.smartdoctor.databinding.DialogAppiontmentDetailBinding
import com.example.smartdoctor.utils.SaveData
import com.example.smartdoctor.viewmodel.medical_test.MedicalTestDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ReserveAppointmentDetailDialog(time:String,date:String,doctor:Int) : DialogFragment() {

    private val viewModel: MedicalTestDetailViewModel by viewModels()
    private lateinit var saveData: SaveData

    lateinit var binding: DialogAppiontmentDetailBinding
    var aTime = time
    var aDate = date
    var aDoctor = doctor
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogAppiontmentDetailBinding.inflate(layoutInflater,container,false)
        saveData = SaveData(context)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            appointmentDate.text = "نوبت شما برای ساعت "+aTime+" روز "+aDate+" می باشد"
        }


        sendRequest()

        viewModel.allUserProfileList.observe(viewLifecycleOwner){
            for(i in it){
                if(i.id == aDoctor){
                    binding.apply {
                        progressBar.visibility = View.GONE
                        doctorName.text = "دکتر "+i.fullName
                        gmcNumber.text = "شماره نظام پزشکی : "+i.gmcNumber
                        doctorAddress.text = "آدرس : "+i.address
                    }
                    break
                }
            }
        }

        binding.apply {
            //create chat
            createChatBtn.setOnClickListener {
                createChat(aDoctor)
            }

        }

        viewModel.newChat.observe(viewLifecycleOwner){
            Toast.makeText(context,"گفت و گو با موفقیت ایجاد شد",Toast.LENGTH_SHORT).show()
        }



    }

    private fun createChat(aDoctor: Int) {
        viewLifecycleOwner.lifecycleScope.launch{
            saveData.getToken.collect{ token ->
                saveData.getProfileId.collect{ profileId ->
                    var chatBodyModel = ChatBodyModel(aDoctor,profileId!!)
                    viewModel.createChat("token $token",chatBodyModel)
                }
            }
        }

    }

    private fun sendRequest() {
        viewLifecycleOwner.lifecycleScope.launch{
            saveData.getToken.collect{ token ->
                saveData.getProfileId.collect{ profileId ->
                    viewModel.getAllUserProfile("token $token")
                }
            }
        }
    }
}