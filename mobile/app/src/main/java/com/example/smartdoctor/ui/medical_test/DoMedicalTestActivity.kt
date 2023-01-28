package com.example.smartdoctor.ui.medical_test

import android.content.res.Resources
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Switch
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.smartdoctor.R
import com.example.smartdoctor.data.model.MedicalTestBodyModel
import com.example.smartdoctor.databinding.ActivityDoMedicalTestBinding
import com.example.smartdoctor.utils.SaveData
import com.example.smartdoctor.viewmodel.chat.ChatDetailViewModel
import com.example.smartdoctor.viewmodel.medical_test.DoMedicalTestViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DoMedicalTestActivity : AppCompatActivity() ,OnItemClickListener{

    lateinit var binding:ActivityDoMedicalTestBinding
    lateinit var arrayAdapter : ArrayAdapter<String>
    var testTypeItems:ArrayList<String> = ArrayList()
    var testTypeItemsEnglish : ArrayList<String> = ArrayList()
    private lateinit var saveData: SaveData

    private val viewModel: DoMedicalTestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoMedicalTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        saveData = SaveData(this)


        val testTypeName=intent.getStringExtra("TestTypeName")
        binding.apply {

            when(testTypeName){
                "skin" -> {
                    for(i in resources.getStringArray(R.array.skin_english)){
                        testTypeItemsEnglish.add(i)
                    }
                    arrayAdapter = ArrayAdapter(
                        applicationContext, android.R.layout.simple_list_item_multiple_choice,
                        resources.getStringArray(R.array.skin_persian)
                    )
                }
                "eye" -> {
                    for(i in resources.getStringArray(R.array.eye_english)){
                        testTypeItemsEnglish.add(i)
                    }
                    arrayAdapter = ArrayAdapter(
                        applicationContext, android.R.layout.simple_list_item_multiple_choice,
                        resources.getStringArray(R.array.eye_persian)
                    )
                }
                "mouth_tooth" -> {
                    for(i in resources.getStringArray(R.array.mouth_tooth_english)){
                        testTypeItemsEnglish.add(i)
                    }
                    arrayAdapter = ArrayAdapter(
                        applicationContext, android.R.layout.simple_list_item_multiple_choice,
                        resources.getStringArray(R.array.mouth_tooth_persian)
                    )
                }
                "heart_chest" -> {
                    for(i in resources.getStringArray(R.array.heart_chest_english)){
                        testTypeItemsEnglish.add(i)
                    }
                    arrayAdapter = ArrayAdapter(
                        applicationContext, android.R.layout.simple_list_item_multiple_choice,
                        resources.getStringArray(R.array.heart_chest_persian)
                    )
                }
                "stomach_digestion" -> {
                    for(i in resources.getStringArray(R.array.stomach_digestion_english)){
                        testTypeItemsEnglish.add(i)
                    }
                    arrayAdapter = ArrayAdapter(
                        applicationContext, android.R.layout.simple_list_item_multiple_choice,
                        resources.getStringArray(R.array.stomach_digestion_persian)
                    )
                }
                "joint_muscle" -> {
                    for(i in resources.getStringArray(R.array.joint_muscle_english)){
                        testTypeItemsEnglish.add(i)
                    }
                    arrayAdapter = ArrayAdapter(
                        applicationContext, android.R.layout.simple_list_item_multiple_choice,
                        resources.getStringArray(R.array.joint_muscle_persian)
                    )
                }
                "throat" -> {
                    for(i in resources.getStringArray(R.array.throat_english)){
                        testTypeItemsEnglish.add(i)
                    }
                    arrayAdapter = ArrayAdapter(
                        applicationContext, android.R.layout.simple_list_item_multiple_choice,
                        resources.getStringArray(R.array.throat_persian)
                    )
                }
                "waist_neck" -> {
                    for(i in resources.getStringArray(R.array.waist_neck_english)){
                        testTypeItemsEnglish.add(i)
                    }
                    arrayAdapter = ArrayAdapter(
                        applicationContext, android.R.layout.simple_list_item_multiple_choice,
                        resources.getStringArray(R.array.waist_neck_persian)
                    )
                }
                "nose" -> {
                    for(i in resources.getStringArray(R.array.nose_english)){
                        testTypeItemsEnglish.add(i)
                    }
                    arrayAdapter = ArrayAdapter(
                        applicationContext, android.R.layout.simple_list_item_multiple_choice,
                        resources.getStringArray(R.array.nose_persian)
                    )
                }
                "excretory_system" -> {
                    for(i in resources.getStringArray(R.array.excretory_system_english)){
                        testTypeItemsEnglish.add(i)
                    }
                    arrayAdapter = ArrayAdapter(
                        applicationContext, android.R.layout.simple_list_item_multiple_choice,
                        resources.getStringArray(R.array.excretory_system_persian)
                    )
                }
                "psychological" -> {
                    for(i in resources.getStringArray(R.array.psychological_english)){
                        testTypeItemsEnglish.add(i)
                    }
                    arrayAdapter = ArrayAdapter(
                        applicationContext, android.R.layout.simple_list_item_multiple_choice,
                        resources.getStringArray(R.array.psychological_persian)
                    )
                }
                "hand_foot" -> {
                    for(i in resources.getStringArray(R.array.hand_foot_english)){
                        testTypeItemsEnglish.add(i)
                    }
                    arrayAdapter = ArrayAdapter(
                        applicationContext, android.R.layout.simple_list_item_multiple_choice,
                        resources.getStringArray(R.array.hand_foot_persian)
                    )
                }
                "movement" -> {
                    for(i in resources.getStringArray(R.array.movement_english)){
                        testTypeItemsEnglish.add(i)
                    }
                    arrayAdapter = ArrayAdapter(
                        applicationContext, android.R.layout.simple_list_item_multiple_choice,
                        resources.getStringArray(R.array.movement_persian )
                    )
                }
                "public" -> {
                    for(i in resources.getStringArray(R.array.public_english)){
                        testTypeItemsEnglish.add(i)
                    }
                    arrayAdapter = ArrayAdapter(
                        applicationContext, android.R.layout.simple_list_item_multiple_choice,
                        resources.getStringArray(R.array.public_persian)
                    )
                }
            }

            multipleListView.adapter = arrayAdapter
            multipleListView.choiceMode = ListView.CHOICE_MODE_MULTIPLE
            multipleListView.onItemClickListener = this@DoMedicalTestActivity

            viewModel.getResponse.observe(this@DoMedicalTestActivity){
                finish()
            }

            viewModel.newMedicalTest.observe(this@DoMedicalTestActivity){
                GlobalScope.launch (Dispatchers.IO){
                    saveData.getProfileId.collect{ profileId->
                        saveData.getToken.collect{token->
                            viewModel.getResponse("token $token",it!!.id)
                        }
                    }
                }

            }


            doTestFAB.setOnClickListener {
                var testBitStream:String =""
                for(i in resources.getStringArray(R.array.all_tests)){
                    var found:Boolean = false
                    for(j in testTypeItems){
                        if(j.equals(i)){
                            found = true
                            break
                        }
                    }
                    if(found){
                        testBitStream+="1"
                    }
                    else{
                        testBitStream+="0"
                    }
                }

                GlobalScope.launch (Dispatchers.IO){
                    saveData.getProfileId.collect{ profileId->
                        saveData.getToken.collect{token->
                            var medicalTestBodyModel = MedicalTestBodyModel(profileId!!,testBitStream)
                            //println("pId${medicalTestBodyModel.user_id} and ${medicalTestBodyModel.test_input}")
                            viewModel.createMedicalTest("token $token",medicalTestBodyModel)
                        }
                    }
                }



                //println("test bitstream : $testBitStream")
            }

        }



    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        val option : String = p0?.getItemAtPosition(p2) as String
        val englishOption :String = testTypeItemsEnglish.get(p2)
        var found:Boolean =false
        for(i in testTypeItems){
            if(i.equals(englishOption)){
                found = true
                break
            }
        }
        if(!found){
            testTypeItems.add(englishOption)
        }


    }
}