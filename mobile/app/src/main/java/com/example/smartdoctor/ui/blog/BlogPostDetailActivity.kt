package com.example.smartdoctor.ui.blog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.smartdoctor.databinding.ActivityBlogPostDetailBinding

class BlogPostDetailActivity : AppCompatActivity() {
    lateinit var binding:ActivityBlogPostDetailBinding
    var title = ""
    var text = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBlogPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = intent.getStringExtra("title").toString()
        text = intent.getStringExtra("text").toString()


        binding.apply {
            titleTxt.text = title
            textTxt.text = text
        }

    }
}