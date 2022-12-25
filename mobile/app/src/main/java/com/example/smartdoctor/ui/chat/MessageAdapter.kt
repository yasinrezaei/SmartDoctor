package com.example.smartdoctor.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.smartdoctor.data.model.ChatsListModel
import com.example.smartdoctor.data.model.DjangoDateTime
import com.example.smartdoctor.data.model.MessageListModel
import com.example.smartdoctor.databinding.ItemChatBinding
import com.example.smartdoctor.databinding.ItemMessageBinding

class MessageAdapter constructor(private val profileId:Int) : RecyclerView.Adapter<MessageAdapter.ViewHolder>(){
    lateinit var binding: ItemMessageBinding
    var onItemClick: ((MessageListModel.MessageItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        fun setData(item: MessageListModel.MessageItem) {
            binding.apply {

                root.setOnClickListener {
                    onItemClick?.invoke(item)
                }

                textGchatDateMe.text = djangoDateTimeSlicer(item.date).date

                if(item.senderId == profileId){
                    textGchatMessageMe.text = item.text
                    textGchatTimestampMe.text = djangoDateTimeSlicer(item.date).time

                    layoutGchatContainerOther.visibility = View.GONE
                    textGchatTimestampMe2.visibility = View.GONE
                }
                else{
                    textGchatMessageOther.text = item.text
                    textGchatTimestampMe2.text = djangoDateTimeSlicer(item.date).time
                    layoutGchatContainerMe.visibility = View.GONE
                    textGchatTimestampMe.visibility =View.GONE
                }







            }
        }
    }
    val differCallBack = object : DiffUtil.ItemCallback<MessageListModel.MessageItem>() {
        override fun areItemsTheSame(oldItem: MessageListModel.MessageItem, newItem: MessageListModel.MessageItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MessageListModel.MessageItem, newItem: MessageListModel.MessageItem): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)

    private fun djangoDateTimeSlicer(djangoDateTime:String): DjangoDateTime {
        return DjangoDateTime(djangoDateTime.substring(0, 9),djangoDateTime.substring(11, 16))
    }


}