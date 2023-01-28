package com.example.smartdoctor.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.smartdoctor.R
import com.example.smartdoctor.data.model.ProfileModel
import com.example.smartdoctor.databinding.FragmentProfileBinding
import com.example.smartdoctor.ui.account.LoginActivity
import com.example.smartdoctor.utils.CheckConnection
import com.example.smartdoctor.utils.SaveData
import com.example.smartdoctor.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {


    lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var saveData: SaveData

    @Inject
    lateinit var connection: CheckConnection

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        saveData = SaveData(context)
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //show progressbar
        showProgressBar()

        //check connection and ...
        checkConnectionAndSendRequest()


        //observe on profile
        viewModel.profile.observe(viewLifecycleOwner){
            setProfileViw(it)
            GlobalScope.launch(Dispatchers.IO) {
                saveData.saveProfileId(it.id)
            }

        }

        //observe on get profile error
        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
        }

        binding.apply {
            logoutBtn.setOnClickListener {

                //clean last token
                GlobalScope.launch(Dispatchers.IO) {
                    saveData.cleanData()
                }
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            }

            editBtn.setOnClickListener {
                var editProfileFragment = EditProfileFragment(viewModel.profile.value)
                editProfileFragment.show(parentFragmentManager,editProfileFragment.tag)

                editProfileFragment.onProfileEdit = {
                    viewModel.profile.postValue(it)
                }

            }
        }

    }

    private fun setProfileViw(it: ProfileModel) {
        binding.apply {
            hideProgressBar()
            val animationFadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
            if(it.gender.equals("male")){
                prodileImage.setBackgroundResource(R.drawable.male)
                prodileImage.startAnimation(animationFadeIn)
            }else{
                prodileImage.setBackgroundResource(R.drawable.female)
                prodileImage.startAnimation(animationFadeIn)
            }

            fullname.text = it.fullName
            fullname.startAnimation(animationFadeIn)
            username.text = it.userId
            username.startAnimation(animationFadeIn)
            address.text = "آدرس : "+it.address
            address.startAnimation(animationFadeIn)
            nationalCode.text ="کد ملی : "+ it.nationalCode
            nationalCode.startAnimation(animationFadeIn)
            city.text = "شهر : "+it.city
            city.startAnimation(animationFadeIn)
            age.text = "سن : "+it.age+" سال"
            age.startAnimation(animationFadeIn)

        }
    }

    private fun checkConnectionAndSendRequest() {

        binding.apply {
            connectionConstraint.visibility = View.VISIBLE
            profileDetailLinear.visibility = View.GONE
            headConstraintLayout.visibility = View.GONE
        }



        connection.observe(viewLifecycleOwner){
            if(it){

                // ok connection
                binding.apply {
                    connectionConstraint.visibility = View.GONE
                    profileDetailLinear.visibility = View.VISIBLE
                    headConstraintLayout.visibility = View.VISIBLE
                }
                GlobalScope.launch(Dispatchers.IO) {
                    saveData.getToken.collect{ token ->
                        saveData.getUserId.collect{ userId ->
                            viewModel.getUserProfile("token $token",userId!!)
                        }
                    }
                }

            }
            //no connection
            else{
                binding.apply {
                    connectionConstraint.visibility = View.VISIBLE
                    profileDetailLinear.visibility = View.GONE
                    headConstraintLayout.visibility = View.GONE
                }
            }
        }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

}