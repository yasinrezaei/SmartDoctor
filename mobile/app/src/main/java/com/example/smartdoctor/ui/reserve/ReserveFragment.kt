package com.example.smartdoctor.ui.reserve

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartdoctor.R
import com.example.smartdoctor.data.model.ReserveModel
import com.example.smartdoctor.databinding.FragmentReserveBinding
import com.example.smartdoctor.ui.medical_test.HelpDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReserveFragment :Fragment() {
    lateinit var binding: FragmentReserveBinding

    @Inject
    lateinit var reserveAdapter: LastReserveAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReserveBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var reserve1 = ReserveModel(1,"دکتر حسین طاهری","تاریخ : 23-02-1401","ساعت : 12:20","شیراز، میدان نمازی، فرعی اول")
        var reserve2 = ReserveModel(2,"دکتر احمد رضایی","تاریخ : 27-04-1401","ساعت : 13:25","شیراز، میدان معلم، فرعی دوم")
        var reserve3 = ReserveModel(3,"دکتر مهدی محمدی","تاریخ : 13-01-1401","ساعت : 16:20","شیراز، حیابان زند ، فرعی دوازده")
        var reserve4 = ReserveModel(4,"دکتر علی طبایی","تاریخ : 27-11-1401","ساعت : 18:00","شیراز، ارم، فرعی دوم")
        var reserve5 = ReserveModel(5,"دکتر جواد صادقی","تاریخ : 12-06-1401","ساعت : 09:20","شیراز،  ملاصدرا، فرعی سوم")


        reserveAdapter.differ.submitList(listOf(reserve1,reserve2,reserve3,reserve4,reserve5))



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




    }
}