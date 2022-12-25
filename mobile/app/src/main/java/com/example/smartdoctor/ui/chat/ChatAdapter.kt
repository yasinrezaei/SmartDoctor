package com.example.smartdoctor.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.smartdoctor.data.model.ChatsListModel
import com.example.smartdoctor.databinding.ItemChatBinding
import javax.inject.Inject


class ChatAdapter @Inject constructor() : RecyclerView.Adapter<ChatAdapter.ViewHolder> (){

    lateinit var binding:ItemChatBinding
    var onItemClick: ((ChatsListModel.chatItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        fun setData(item: ChatsListModel.chatItem) {
            binding.apply {

                root.setOnClickListener {
                    onItemClick?.invoke(item)
                }
                userName.text = item.doctorId

            }
        }
    }
    val differCallBack = object : DiffUtil.ItemCallback<ChatsListModel.chatItem>() {
        override fun areItemsTheSame(oldItem: ChatsListModel.chatItem, newItem: ChatsListModel.chatItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChatsListModel.chatItem, newItem: ChatsListModel.chatItem): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)
}