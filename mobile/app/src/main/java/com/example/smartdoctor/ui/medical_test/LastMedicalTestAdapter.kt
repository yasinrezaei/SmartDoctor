package com.example.smartdoctor.ui.medical_test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.smartdoctor.data.model.ChatsListModel
import com.example.smartdoctor.data.model.TestModel
import com.example.smartdoctor.databinding.ItemChatBinding
import com.example.smartdoctor.databinding.ItemLastMedicalTestBinding
import javax.inject.Inject

class LastMedicalTestAdapter @Inject constructor():RecyclerView.Adapter<LastMedicalTestAdapter.ViewHolder>() {
    lateinit var binding:ItemLastMedicalTestBinding
    var onItemClick:((TestModel)->Unit)? = null

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
        fun setData(item: TestModel) {
            binding.apply {

                root.setOnClickListener {
                    onItemClick?.invoke(item)
                }
                testTitle.text = item.title
                testDate.text = item.date
                testTime.text = item.time

            }
        }
    }
    val differCallBack = object : DiffUtil.ItemCallback<TestModel>() {
        override fun areItemsTheSame(oldItem: TestModel, newItem:TestModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TestModel, newItem: TestModel): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)
}