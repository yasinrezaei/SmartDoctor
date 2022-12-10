package com.example.smartdoctor.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smartdoctor.databinding.FragmentProfileBinding
import com.example.smartdoctor.ui.MainActivity
import com.example.smartdoctor.ui.account.LoginActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            logoutBtn.setOnClickListener {
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            }

            editBtn.setOnClickListener {
                EditProfileFragment().show(parentFragmentManager,EditProfileFragment().tag)
            }
        }
    }
}