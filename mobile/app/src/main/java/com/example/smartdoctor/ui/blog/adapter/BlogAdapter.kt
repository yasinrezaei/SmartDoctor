package com.example.smartdoctor.ui.blog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.smartdoctor.data.model.BlogPostListModel
import com.example.smartdoctor.data.model.ChatsListModel
import com.example.smartdoctor.data.model.DjangoDateTime
import com.example.smartdoctor.databinding.ItemChatBinding
import com.example.smartdoctor.databinding.ItemPostBinding
import com.example.smartdoctor.ui.chat.ChatAdapter
import javax.inject.Inject

class BlogAdapter @Inject constructor() : RecyclerView.Adapter<BlogAdapter.ViewHolder> () {
    lateinit var binding: ItemPostBinding
    var onItemClick: ((BlogPostListModel.BlogPostItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        fun setData(item: BlogPostListModel.BlogPostItem) {
            binding.apply {

                root.setOnClickListener {
                    onItemClick?.invoke(item)
                }

                postTitle.text = item.title
                postDate.text = djangoDateTimeSlicer(item.date).date

            }
        }
    }
    val differCallBack = object : DiffUtil.ItemCallback<BlogPostListModel.BlogPostItem>() {
        override fun areItemsTheSame(oldItem: BlogPostListModel.BlogPostItem, newItem: BlogPostListModel.BlogPostItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BlogPostListModel.BlogPostItem, newItem: BlogPostListModel.BlogPostItem): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)

    private fun djangoDateTimeSlicer(djangoDateTime:String): DjangoDateTime {
        return DjangoDateTime(djangoDateTime.substring(0, 9),djangoDateTime.substring(11, 16))
    }
}