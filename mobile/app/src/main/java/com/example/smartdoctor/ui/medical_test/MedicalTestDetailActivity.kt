package com.example.smartdoctor.ui.medical_test

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.smartdoctor.databinding.ActivityMedicalTestDetailBinding
import com.example.smartdoctor.utils.SaveData
import com.example.smartdoctor.viewmodel.chat.ChatViewModel
import com.example.smartdoctor.viewmodel.medical_test.MedicalTestDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MedicalTestDetailActivity : AppCompatActivity() {


    lateinit var binding:ActivityMedicalTestDetailBinding

    private val viewModel: MedicalTestDetailViewModel by viewModels()
    private lateinit var saveData: SaveData
    var testId :Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicalTestDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        saveData = SaveData(this)
        testId = intent.getIntExtra("test_id",0)

        //check connection and send request
        checkConnectionAndSendRequest()



        viewModel.medicalTestResponse.observe(this){
            binding.apply {
                testIdTxt.text = "تست شماره "+testId
                diseaseTypeTxt.text = "بیماری تشخیص داده شده : "+it.testResponse
                helpTxt.visibility = View.VISIBLE
                reserveBtn.visibility = View.VISIBLE
            }
        }


    }

    private fun checkConnectionAndSendRequest() {
        lifecycleScope.launch{
            saveData.getToken.collect{
                viewModel.getMedicalTestResponseDetail("token $it" ,testId)
            }
        }
    }


}