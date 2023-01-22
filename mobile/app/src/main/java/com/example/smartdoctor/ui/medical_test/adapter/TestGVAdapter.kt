package com.asemantile.app.ui.main.socialmedia.profile

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.smartdoctor.R
import com.example.smartdoctor.data.model.TestTypeModel
import com.example.smartdoctor.ui.medical_test.DoMedicalTestActivity

internal class TestTypeGVAdapter(
    // on below line we are creating two
    // variables for course list and context
    private val testTypeList: List<TestTypeModel>,
    private val context: Context
) :
    BaseAdapter() {
    // in base adapter class we are creating variables
    // for layout inflater, course image view and course text view.
    private var layoutInflater: LayoutInflater? = null
    private lateinit var testTypeName: TextView
    private lateinit var testTypeImage: ImageView

    // below method is use to return the count of course list
    override fun getCount(): Int {
        return testTypeList.size
    }

    // below function is use to return the item of grid view.
    override fun getItem(position: Int): Any? {
        return null
    }

    // below function is use to return item id of grid view.
    override fun getItemId(position: Int): Long {
        return 0
    }

    // in below function we are getting individual item of grid view.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        // on blow line we are checking if layout inflater
        // is null, if it is null we are initializing it.
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        // on the below line we are checking if convert view is null.
        // If it is null we are initializing it.
        if (convertView == null) {
            // on below line we are passing the layout file
            // which we have to inflate for each item of grid view.
            convertView = layoutInflater!!.inflate(R.layout.item_test_type, null)
        }
        // on below line we are initializing our course image view
        // and course text view with their ids.
        testTypeName = convertView!!.findViewById(R.id.testTypeName)
        testTypeImage = convertView!!.findViewById(R.id.testTypeImage)
        // on below line we are setting image for our course image view.
        testTypeImage.setImageResource(testTypeList.get(position).picId)
        // on below line we are setting text in our course text view.
        testTypeName.setText(testTypeList.get(position).typePersianName)
        // at last we are returning our convert view.

        testTypeImage.setOnClickListener {
            Toast.makeText(context,testTypeList.get(position).typeEnglishName,Toast.LENGTH_SHORT).show()
            val intent = Intent(context,DoMedicalTestActivity::class.java)
            intent.putExtra("TestTypeName",testTypeList.get(position).typeEnglishName)
            context.startActivity(intent)
        }


        return convertView
    }
}