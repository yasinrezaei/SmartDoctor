package com.example.smartdoctor.ui.profile

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.smartdoctor.R
import com.example.smartdoctor.data.model.CityListModel
import com.example.smartdoctor.data.model.ProfileBodyModel
import com.example.smartdoctor.data.model.ProfileModel
import com.example.smartdoctor.data.repository.OtherRepository
import com.example.smartdoctor.databinding.FragmentEditProfileBinding
import com.example.smartdoctor.utils.SaveData
import com.example.smartdoctor.viewmodel.profile.EditProfileViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class EditProfileFragment(var profile: ProfileModel?) : BottomSheetDialogFragment() {
    lateinit var binding:FragmentEditProfileBinding
    var onProfileEdit: ((ProfileModel) -> Unit)? = null

    private lateinit var saveData: SaveData
    private val viewModel: EditProfileViewModel by viewModels()
    private var cityId:Int = 0
    private var gender:String = ""


    @Inject
    lateinit var otherRepository: OtherRepository

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

        val checkedItem = intArrayOf(-1)

        binding.apply {

            edtFullName.setText(profile?.fullName ?: " ")
            edtNationalCode.setText(profile?.nationalCode?:" ")
            edtAge.setText(profile?.age.toString())
            edtCity.setText(profile?.city)
            edtAddress.setText(profile?.address)
            cityId = profile!!.cityId
            gender = profile!!.gender

            txtGender.setText("جنیست : "+profile?.gender)
            when(profile?.gender){
                "male" -> {
                    txtGender.setText("جنیست : مرد")
                }
                "female" -> {
                    txtGender.setText("جنیست : زن")
                }
                "other" -> {
                    txtGender.setText("جنیست : دیگر")
                }
            }
            viewModel.editedProfile.observe(viewLifecycleOwner){
                onProfileEdit!!.invoke(it)
                dismiss()
            }


            editCityBtn.setOnClickListener {
                viewModel.loadAllCitiesList()
                viewModel.citiesList.observe(viewLifecycleOwner){
                    val alertDialog = AlertDialog.Builder(context)
                    alertDialog.setIcon(R.drawable.ic_baseline_edit_location_alt_24)
                    alertDialog.setTitle("انتخاب شهر")

                    val listItems = Array<CharSequence>(it.size){ i->
                        it[i].cityName
                    }

                    alertDialog.setSingleChoiceItems(listItems, checkedItem[0]) { dialog, which ->
                        checkedItem[0] = which
                        edtCity.setText(" " + listItems[which])
                        cityId = it[which].id
                        dialog.dismiss()
                    }
                    alertDialog.show()
                }


            }


            txtGender.setOnClickListener {
                val alertDialog = AlertDialog.Builder(context)
                alertDialog.setIcon(R.drawable.ic_baseline_transgender_24)
                alertDialog.setTitle("انتخاب جنسیت")

                val listItems = arrayOf("مرد","زن","سایر")

                alertDialog.setSingleChoiceItems(listItems, checkedItem[0]) { dialog, which ->
                    checkedItem[0] = which
                    txtGender.setText("جنسیت : " + listItems[which])
                    when(which){
                        0 -> {
                            gender = "male"
                        }
                        1 -> {
                            gender = "female"
                        }
                        2 -> {
                            gender = "other"
                        }
                    }
                    dialog.dismiss()
                }
                alertDialog.show()
            }






            editProfileBtn.setOnClickListener {
                var newProfile = ProfileBodyModel(edtAddress.text.toString(),Integer. parseInt(edtAge.text.toString()),
                    cityId,edtFullName.text.toString(),gender,"",edtNationalCode.text.toString())


                viewLifecycleOwner.lifecycleScope.launch{
                    saveData.getToken.collect{
                        viewModel.editUserProfile("token $it", profile!!.id,newProfile)

                    }
                }


            }
        }
    }
}