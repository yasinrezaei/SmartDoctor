package com.example.smartdoctor.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.smartdoctor.R
import com.example.smartdoctor.databinding.FragmentProfileBinding
import com.example.smartdoctor.ui.account.LoginActivity
import com.example.smartdoctor.utils.SaveData
import com.example.smartdoctor.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {


    lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var tokenValue:String
    private lateinit var saveData: SaveData



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
        showProgressBar()


        viewModel.profile.observe(viewLifecycleOwner){
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

        GlobalScope.launch(Dispatchers.IO) {
            saveData.getToken.collect{
                viewModel.getUserProfile("token $it",16)
            }
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
                EditProfileFragment(viewModel.profile.value).show(parentFragmentManager,EditProfileFragment(viewModel.profile.value).tag)
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