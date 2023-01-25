package com.example.smartdoctor.ui.reserve

import android.os.Binder
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.smartdoctor.data.model.BookingBodyModel
import com.example.smartdoctor.data.model.BookingListModel
import com.example.smartdoctor.databinding.ActivityReserveAppointmentBinding
import com.example.smartdoctor.utils.SaveData
import com.example.smartdoctor.viewmodel.medical_test.ReserveAppointmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReserveAppointmentActivity : AppCompatActivity() {

    lateinit var binding:ActivityReserveAppointmentBinding
    private val viewModel: ReserveAppointmentViewModel by viewModels()
    private lateinit var saveData: SaveData
    var bookingList = ArrayList<BookingListModel.BookingItem>()


    var doctorId =0
    var doctorName = ""
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReserveAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.timePicker1.setIs24HourView(true)


        saveData = SaveData(this)
        binding.constraint.visibility = View.GONE

        //get doctor id and name from intent
        doctorId = intent.getIntExtra("doctor_id",0)
        doctorName = intent.getStringExtra("doctor_name").toString()

        //check connection and send request
        checkConnectionAndSendRequest()


        //observe on booking list
        viewModel.doctorBookingsList.observe(this){
            bookingList = it

            binding.apply {
                constraint.visibility = View.VISIBLE
                doctorNameTxt.text = "رزرو نوبت برای دکتر "+doctorName
            }
        }


        //reserve btn
        binding.apply {
            reserveBtn.setOnClickListener {
                var year:Int = datePicker1.year
                var month:Int = datePicker1.month+1
                var day:Int = datePicker1.dayOfMonth


                var hour:Int = timePicker1.hour
                var minute:Int = timePicker1.minute



                var fullTime:String = ""+dateTimeZeroAdder(hour)+":"+dateTimeZeroAdder(minute)+":00"
                var fullDate:String = ""+year+"-"+dateTimeZeroAdder(month)+"-"+dateTimeZeroAdder(day)


                Log.e("time",fullTime+"---"+fullDate)
                ReserveStatus(fullTime,fullDate)


            }
        }

        //observer on successful booking
        viewModel.newBooking.observe(this){
            finish()
        }











    }

    private fun ReserveStatus(time:String,date:String){
        var reserved = false
        for(i in bookingList){
            if(i.date.equals(date)){
                if(checkTimeInterference(i.time,time)){
                    reserved = true
                }
            }
        }
        if(reserved){
            Toast.makeText(this,"این زمان قبلا رزرو شد",Toast.LENGTH_SHORT).show()
        }else{

            lifecycleScope.launch {
                saveData.getToken.collect{ token ->
                    saveData.getProfileId.collect{ profileId->
                        var bookingBodyModel = BookingBodyModel(date,doctorId,time,profileId!!)
                        viewModel.createBooking("token $token",bookingBodyModel)
                    }

                }
            }


            Toast.makeText(this,"با موفقیت رزرو شد",Toast.LENGTH_SHORT).show()
        }


    }

    private fun checkTimeInterference(time: String, myTime: String):Boolean {
        if((time.get(0)+""+time.get(1)).equals(myTime.get(0)+""+myTime.get(1))){
            return true
        }
        return false

    }

    private fun checkConnectionAndSendRequest() {
        lifecycleScope.launch {
            saveData.getToken.collect{ token ->
                viewModel.loadDoctorBookingsList("token $token" , doctorId)
            }
        }
    }

    private fun dateTimeZeroAdder(x:Int):String{
        if(x<10){
            return "0"+x
        }
        return ""+x
    }
}