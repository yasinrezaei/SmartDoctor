package com.example.smartdoctor.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartdoctor.data.model.ProfileModel
import com.example.smartdoctor.databinding.FragmentEditProfileBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditProfileFragment(var profile: ProfileModel?) : BottomSheetDialogFragment() {
    lateinit var binding:FragmentEditProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            edtFullName.setText(profile?.fullName ?: " ")
            edtNationalCode.setText(profile?.nationalCode?:" ")
            edtAge.setText(profile?.age.toString())
            edtCity.setText(profile?.city)
            edtAddress.setText(profile?.address)



            editProfileBtn.setOnClickListener {
                dismiss()
            }
        }
    }
}