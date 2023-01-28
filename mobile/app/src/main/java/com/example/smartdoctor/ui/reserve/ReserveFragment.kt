package com.example.smartdoctor.ui.reserve

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
import com.example.smartdoctor.data.model.ReserveModel
import com.example.smartdoctor.databinding.FragmentReserveBinding
import com.example.smartdoctor.ui.medical_test.HelpDialogFragment
import com.example.smartdoctor.utils.SaveData
import com.example.smartdoctor.viewmodel.medical_test.MedicalTestViewModel
import com.example.smartdoctor.viewmodel.reserve.ReserveViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ReserveFragment :Fragment() {
    lateinit var binding: FragmentReserveBinding

    @Inject
    lateinit var reserveAdapter: LastReserveAdapter

    private val viewModel: ReserveViewModel by viewModels()
    private lateinit var saveData: SaveData


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReserveBinding.inflate(inflater,container,false)
        saveData = SaveData(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //check connection and send request
        checkConnectionAndSendRequest()

        viewModel.bookingList.observe(viewLifecycleOwner){
            reserveAdapter.differ.submitList(it)
        }



        binding.apply {
            lastReserveRecycler.apply {
                adapter = reserveAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            }


            help.setOnClickListener {
                HelpDialogFragment(getString(R.string.help_text_reserve)).show(parentFragmentManager, HelpDialogFragment(getString(
                    R.string.help_text_chat)).tag)

            }
        }

        reserveAdapter.onItemClick = {
            ReserveAppointmentDetailDialog(it.time,it.date,it.doctor).show(
                parentFragmentManager,
                ReserveAppointmentDetailDialog(it.time,it.date,it.doctor).tag
            )
        }




    }

    private fun checkConnectionAndSendRequest() {
        viewLifecycleOwner.lifecycleScope.launch{
            saveData.getToken.collect{ token ->
                saveData.getProfileId.collect{ profileId ->
                    viewModel.loadBookingList("token $token" , profileId!!)
                }
            }
        }
    }
}