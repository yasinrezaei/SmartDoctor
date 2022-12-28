package com.example.smartdoctor.ui.profile

import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.smartdoctor.data.model.MessageListModel
import com.example.smartdoctor.data.model.ProfileBodyModel
import com.example.smartdoctor.data.model.ProfileModel
import com.example.smartdoctor.data.repository.AccountRepository
import com.example.smartdoctor.data.server.ApiService
import com.example.smartdoctor.databinding.FragmentEditProfileBinding
import com.example.smartdoctor.utils.SaveData
import com.example.smartdoctor.viewmodel.chat.ChatViewModel
import com.example.smartdoctor.viewmodel.profile.EditProfileViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class EditProfileFragment(var profile: ProfileModel?) : BottomSheetDialogFragment() {
    lateinit var binding:FragmentEditProfileBinding
    var onProfileEdit: ((ProfileModel) -> Unit)? = null

    private lateinit var saveData: SaveData

    private val viewModel: EditProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        saveData = SaveData(context)
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


            viewModel.editedProfile.observe(viewLifecycleOwner){
                onProfileEdit!!.invoke(it)
                dismiss()
            }



            editProfileBtn.setOnClickListener {
                var newProfile = ProfileBodyModel(edtAddress.text.toString(),Integer. parseInt(edtAge.text.toString()),1,edtFullName.text.toString(),"female","111",edtNationalCode.text.toString())


                viewLifecycleOwner.lifecycleScope.launch{
                    saveData.getToken.collect{
                        viewModel.editUserProfile("token $it",16,newProfile)

                    }
                }


            }
        }
    }
}