package com.example.smartdoctor.data.model


import com.google.gson.annotations.SerializedName

class BlogPostListModel : ArrayList<BlogPostListModel.BlogPostItem>(){
    data class BlogPostItem(
        @SerializedName("date")
        val date: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("text")
        val text: String,
        @SerializedName("title")
        val title: String
    )
}