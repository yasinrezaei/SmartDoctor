package com.example.smartdoctor.ui.medical_test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.smartdoctor.data.model.ChatsListModel
import com.example.smartdoctor.data.model.UserProfileListModel
import com.example.smartdoctor.databinding.ItemChatBinding
import com.example.smartdoctor.databinding.ItemDoctorBinding
import com.example.smartdoctor.ui.chat.ChatAdapter
import javax.inject.Inject

class SuggestedDoctorsAdapter @Inject constructor() : RecyclerView.Adapter<SuggestedDoctorsAdapter.ViewHolder>() {
    lateinit var binding:ItemDoctorBinding
    var onItemClick: ((UserProfileListModel.UserProfileItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestedDoctorsAdapter.ViewHolder {
        binding = ItemDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }
    override fun onBindViewHolder(holder:SuggestedDoctorsAdapter.ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun setData(item: UserProfileListModel.UserProfileItem) {
            binding.apply {

                root.setOnClickListener {
                    onItemClick?.invoke(item)
                }

                doctorName.text ="دکتر "+ item.fullName
                doctorAddress.text = "آدرس : "+item.address

            }
        }
    }
    val differCallBack = object : DiffUtil.ItemCallback<UserProfileListModel.UserProfileItem>() {
        override fun areItemsTheSame(oldItem: UserProfileListModel.UserProfileItem, newItem: UserProfileListModel.UserProfileItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserProfileListModel.UserProfileItem, newItem: UserProfileListModel.UserProfileItem): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)
}