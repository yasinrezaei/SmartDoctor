package com.example.smartdoctor.ui.medical_test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.smartdoctor.data.model.DjangoDateTime
import com.example.smartdoctor.data.model.MedicalTestListModel
import com.example.smartdoctor.databinding.ItemLastMedicalTestBinding
import javax.inject.Inject

class LastMedicalTestAdapter @Inject constructor():RecyclerView.Adapter<LastMedicalTestAdapter.ViewHolder>() {
    lateinit var binding:ItemLastMedicalTestBinding
    var onItemClick:((MedicalTestListModel.MedicalTestItem)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemLastMedicalTestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun setData(item: MedicalTestListModel.MedicalTestItem) {
            binding.apply {

                root.setOnClickListener {
                    onItemClick?.invoke(item)
                }
                testTitle.text = "تست شماره"+item.id
                testDate.text = djangoDateTimeSlicer(item.date).date
                testTime.text = djangoDateTimeSlicer(item.date).time

            }
        }
    }
    val differCallBack = object : DiffUtil.ItemCallback<MedicalTestListModel.MedicalTestItem>() {
        override fun areItemsTheSame(oldItem: MedicalTestListModel.MedicalTestItem, newItem:MedicalTestListModel.MedicalTestItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MedicalTestListModel.MedicalTestItem, newItem: MedicalTestListModel.MedicalTestItem): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)

    private fun djangoDateTimeSlicer(djangoDateTime:String): DjangoDateTime {
        return DjangoDateTime(djangoDateTime.substring(0, 10),djangoDateTime.substring(11, 16))
    }
}