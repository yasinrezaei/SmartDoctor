package com.example.smartdoctor.ui.medical_test

import android.net.wifi.WifiConfiguration.AuthAlgorithm.strings
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smartdoctor.R
import com.example.smartdoctor.databinding.FragmentMedicalTestBinding

class MedicalTestFragment :Fragment() {
    lateinit var binding: FragmentMedicalTestBinding
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
            help.setOnClickListener {
                HelpDialogFragment(getString(R.string.help_text_medical_test)).show(parentFragmentManager,HelpDialogFragment(getString(R.string.help_text_medical_test)).tag)
            }
        }
    }
}