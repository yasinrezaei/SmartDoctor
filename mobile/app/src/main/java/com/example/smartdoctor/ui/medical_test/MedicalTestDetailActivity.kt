package com.example.smartdoctor.ui.medical_test

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartdoctor.R
import com.example.smartdoctor.data.model.UserProfileListModel
import com.example.smartdoctor.databinding.ActivityMedicalTestDetailBinding
import com.example.smartdoctor.ui.medical_test.adapter.SuggestedDoctorsAdapter
import com.example.smartdoctor.ui.reserve.ReserveAppointmentActivity
import com.example.smartdoctor.utils.SaveData
import com.example.smartdoctor.viewmodel.chat.ChatViewModel
import com.example.smartdoctor.viewmodel.medical_test.MedicalTestDetailViewModel
import com.example.smartdoctor.viewmodel.medical_test.ReserveAppointmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MedicalTestDetailActivity : AppCompatActivity() {


    lateinit var binding:ActivityMedicalTestDetailBinding

    private val viewModel: MedicalTestDetailViewModel by viewModels()
    private lateinit var saveData: SaveData
    var testId :Int = 0
    var expertise:String = ""
    @Inject
    lateinit var suggestedDoctorsAdapter: SuggestedDoctorsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicalTestDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        saveData = SaveData(this)
        testId = intent.getIntExtra("test_id",0)



        suggestedDoctorsAdapter.onItemClick = {
            val intent = Intent(this,ReserveAppointmentActivity::class.java)
            intent.putExtra("doctor_id",it.id)
            intent.putExtra("doctor_name",it.fullName)
            startActivity(intent)
        }

        //suggested doctor recycler
        binding.apply {
            sugestedDoctorsRecycler.apply {
                adapter = suggestedDoctorsAdapter
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            }
        }

        //check connection and send request
        checkConnectionAndSendRequest()



        viewModel.medicalTestResponse.observe(this){
            binding.apply {
                testIdTxt.text = "تست شماره "+testId

                var counter = 0
                for(i in resources.getStringArray(R.array.desease_english)){
                    if(it.testResponse.equals(i)){
                        break
                    }
                    counter++
                }
                diseaseTypeTxt.text = "بیماری تشخیص داده شده : "+ resources.getStringArray(R.array.desease_persian).get(counter)
                helpTxt.visibility = View.VISIBLE


                //choose expertise
                expertise = findExpertise(it.testResponse)

                loadSuggestedDoctorList()

            }
        }


        viewModel.allUserProfileList.observe(this){

            var uerProfileListModel = UserProfileListModel()
            for(i in it){
                if(i.medicalExpertise.equals(expertise)){
                    uerProfileListModel.add(i)
                }
            }

            suggestedDoctorsAdapter.differ.submitList(uerProfileListModel)
        }

    }



    private fun checkConnectionAndSendRequest() {
        lifecycleScope.launch{
            saveData.getToken.collect{
                viewModel.getMedicalTestResponseDetail("token $it" ,testId)
            }
        }

    }

    private fun loadSuggestedDoctorList(){
        lifecycleScope.launch{
            saveData.getToken.collect{ token ->
                viewModel.getAllUserProfile("token $token" )
            }
        }
    }


    private fun findExpertise(testResponse: String): String {
        for(i in resources.getStringArray(R.array.skin_expertise)){
            if( i.equals(testResponse)){
                return "skin"
            }
        }
        for(i in resources.getStringArray(R.array.public_expertise)){
            if( i.equals(testResponse)){
                return "public"
            }
        }

        for(i in resources.getStringArray(R.array.digestion_expertise)){
            if( i.equals(testResponse)){
                return "digestion"
            }
        }
        for(i in resources.getStringArray(R.array.internist_expertise)){
            if( i.equals(testResponse)){
                return "internist"
            }
        }
        for(i in resources.getStringArray(R.array.blood_expertise)){
            if( i.equals(testResponse)){
                return "blood"
            }
        }


        for(i in resources.getStringArray(R.array.breathing_expertise)){
            if( i.equals(testResponse)){
                return "breathing"
            }
        }
        for(i in resources.getStringArray(R.array.physiotherapist_expertise)){
            if( i.equals(testResponse)){
                return "physiotherapist"
            }
        }
        for(i in resources.getStringArray(R.array.children_expertise)){
            if( i.equals(testResponse)){
                return "children"
            }
        }
        for(i in resources.getStringArray(R.array.heart_expertise)){
            if( i.equals(testResponse)){
                return "heart"
            }
        }

        for(i in resources.getStringArray(R.array.glands_expertise)){
            if( i.equals(testResponse)){
                return "glands"
            }
        }
        for(i in resources.getStringArray(R.array.kidney_expertise)){
            if( i.equals(testResponse)){
                return "kidney"
            }
        }
        return ""

    }


}