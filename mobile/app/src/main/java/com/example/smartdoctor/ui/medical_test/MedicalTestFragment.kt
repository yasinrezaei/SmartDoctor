package com.example.smartdoctor.ui.medical_test

import android.net.wifi.WifiConfiguration.AuthAlgorithm.strings
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.smartdoctor.R
import com.example.smartdoctor.data.model.TestModel
import com.example.smartdoctor.databinding.FragmentMedicalTestBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MedicalTestFragment :Fragment() {
    lateinit var binding: FragmentMedicalTestBinding

    @Inject
    lateinit var testAdapter: LastMedicalTestAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMedicalTestBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            var testModel1 = TestModel(1,"تست شماره 1","تاریخ : 02-04-1401","ساعت : 12:30")
            var testModel2 = TestModel(2,"تست شماره 2","تاریخ : 08-04-1401","ساعت : 22:28")
            var testModel3 = TestModel(3,"تست شماره 3","تاریخ : 09-06-1401","ساعت : 12:45")
            var testModel4 = TestModel(4,"تست شماره 4","تاریخ : 11-02-1401","ساعت : 18:30")
            var testModel5 = TestModel(5,"تست شماره 5","تاریخ : 03-08-1401","ساعت : 21:25")

            var list = listOf(testModel1,testModel2,testModel3,testModel4,testModel5)
            testAdapter.differ.submitList(list)


            help.setOnClickListener {
                HelpDialogFragment(getString(R.string.help_text_medical_test)).show(parentFragmentManager,HelpDialogFragment(getString(R.string.help_text_medical_test)).tag)
            }

            lastTestRecycler.apply {
                adapter = testAdapter
                layoutManager  = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            }

        }


    }
}