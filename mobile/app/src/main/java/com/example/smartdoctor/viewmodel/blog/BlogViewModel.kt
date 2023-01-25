package com.example.smartdoctor.viewmodel.blog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartdoctor.data.model.BlogPostListModel
import com.example.smartdoctor.data.model.ChatsListModel
import com.example.smartdoctor.data.repository.ChatRepository
import com.example.smartdoctor.data.repository.OtherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(private val repository: OtherRepository) : ViewModel(){
    val blogPostList = MutableLiveData<BlogPostListModel>()
    val errorOrEmpty = MutableLiveData<String>()

    fun loadBlogPostList() = viewModelScope.launch (Dispatchers.IO){
        repository.getAllBlogPosts().collect{
            if(it.isSuccessful){
                blogPostList.postValue(it.body())
            }
            else if(it.body()!!.isEmpty()){
                errorOrEmpty.postValue("هیچ مقاله آموزشی وجود ندارد!")
            }
            else{
                errorOrEmpty.postValue("خطا در دریافت مقاله ها!")
            }

        }
    }
}