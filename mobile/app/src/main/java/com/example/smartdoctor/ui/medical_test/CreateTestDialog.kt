package com.example.smartdoctor.ui.medical_test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.asemantile.app.ui.main.socialmedia.profile.TestTypeGVAdapter
import com.example.smartdoctor.R
import com.example.smartdoctor.data.model.TestTypeModel
import com.example.smartdoctor.databinding.DialogCreateTestBinding


class CreateTestDialog : DialogFragment() {
    lateinit var binding: DialogCreateTestBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogCreateTestBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val testTypeModelArrayList: ArrayList<TestTypeModel> = ArrayList<TestTypeModel>()

        testTypeModelArrayList.add(TestTypeModel(1, "دست و پا","hand_foot", R.drawable.body_arm))
        testTypeModelArrayList.add(TestTypeModel(2, "چشم","eye",R.drawable.body_eye))
        testTypeModelArrayList.add(TestTypeModel(3, "دهان و دندان","mouth_tooth",R.drawable.body_mouth))
        testTypeModelArrayList.add(TestTypeModel(4, "قلب و قفسه سینه","heart_chest",R.drawable.body_heart))
        testTypeModelArrayList.add(TestTypeModel(5, "شکمی و گوارش","stomach_digestion",R.drawable.body_colon))
        testTypeModelArrayList.add(TestTypeModel(6, "مفصل و عضله","joint_muscle",R.drawable.body_skeleton))
        testTypeModelArrayList.add(TestTypeModel(7, "گلو","throat",R.drawable.body_sophagus))
        testTypeModelArrayList.add(TestTypeModel(8, "کمر و گردن","waist_neck",R.drawable.body_public))
        testTypeModelArrayList.add(TestTypeModel(9, " بینی","nose",R.drawable.body_nose))
        testTypeModelArrayList.add(TestTypeModel(10, " سیستم دفع","excretory_system",R.drawable.body_intestine))
        testTypeModelArrayList.add(TestTypeModel(11, " روحی و روانی ","psychological",R.drawable.body_skull))
        testTypeModelArrayList.add(TestTypeModel(12, " پوست ","skin",R.drawable.body_skin))
        testTypeModelArrayList.add(TestTypeModel(13, " حرکتی ","movement",R.drawable.body_spine))
        testTypeModelArrayList.add(TestTypeModel(14, " علائم عمومی ","public",R.drawable.body_dna))


        val adapter = TestTypeGVAdapter(testTypeModelArrayList,view.context)


        binding.apply {
            testTypesGrid.adapter = adapter

        }
    }
}