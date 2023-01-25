package com.example.smartdoctor.ui.reserve

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.smartdoctor.data.model.BookingListModel
import com.example.smartdoctor.data.model.ReserveModel
import com.example.smartdoctor.databinding.ItemReserveBinding
import javax.inject.Inject

class LastReserveAdapter @Inject constructor() :RecyclerView.Adapter<LastReserveAdapter.ViewHolder>() {
    lateinit var binding:ItemReserveBinding
    var onItemClick:((BookingListModel.BookingItem)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemReserveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        fun setData(item: BookingListModel.BookingItem) {
            binding.apply {

                root.setOnClickListener {
                    onItemClick?.invoke(item)
                }
                testTitle.text = "دکتر "+item.doctorFullName
                reserveDate.text = "تاریخ : "+item.date
                reserveTime.text = "ساعت : "+item.time


            }
        }
    }
    val differCallBack = object : DiffUtil.ItemCallback<BookingListModel.BookingItem>() {
        override fun areItemsTheSame(oldItem: BookingListModel.BookingItem, newItem:BookingListModel.BookingItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BookingListModel.BookingItem, newItem: BookingListModel.BookingItem): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)
}