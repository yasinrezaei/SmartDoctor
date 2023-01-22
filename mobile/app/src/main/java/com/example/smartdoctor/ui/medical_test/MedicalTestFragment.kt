package com.example.smartdoctor.ui.medical_test

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.smartdoctor.R
import com.example.smartdoctor.data.model.MedicalTestListModel
import com.example.smartdoctor.databinding.FragmentMedicalTestBinding
import com.example.smartdoctor.utils.CheckConnection
import com.example.smartdoctor.utils.SaveData
import com.example.smartdoctor.viewmodel.medical_test.MedicalTestViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MedicalTestFragment : Fragment() {

    lateinit var binding: FragmentMedicalTestBinding
    @Inject
    lateinit var testAdapter: LastMedicalTestAdapter
    private val viewModel: MedicalTestViewModel by viewModels()
    private lateinit var saveData: SaveData
    @Inject
    lateinit var connection: CheckConnection



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMedicalTestBinding.inflate(inflater, container, false)
        saveData = SaveData(context)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            //check connection and send request
            checkConnectionAndSendRequest()

            //observe on medical tests list
            viewModel.medicalTestsList.observe(viewLifecycleOwner){
                testAdapter.differ.submitList(it)
            }


            //observe on error or empty
            viewModel.errorOrEmpty.observe(viewLifecycleOwner){
                Toast.makeText(context,it, Toast.LENGTH_SHORT).show()
            }


            testAdapter.onItemClick ={
                val intent = Intent(context,MedicalTestDetailActivity::class.java)
                intent.putExtra("test_id",it.id)
                context?.startActivity(intent)
            }


            help.setOnClickListener {
                HelpDialogFragment(getString(R.string.help_text_medical_test)).show(
                    parentFragmentManager,
                    HelpDialogFragment(getString(R.string.help_text_medical_test)).tag
                )
            }

            lastTestRecycler.apply {
                adapter = testAdapter
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            }

            createNewTestBtn.setOnClickListener {
                CreateTestDialog().show(parentFragmentManager, CreateTestDialog().tag)
            }

        }


    }

    private fun checkConnectionAndSendRequest(){
        viewLifecycleOwner.lifecycleScope.launch{
            saveData.getToken.collect{ token ->
                saveData.getProfileId.collect{ profileId ->
                    viewModel.loadMedicalTests("token $token" , profileId!!)
                }
            }
        }
    }
}