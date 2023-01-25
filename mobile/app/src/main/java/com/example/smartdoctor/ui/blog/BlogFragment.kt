package com.example.smartdoctor.ui.blog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartdoctor.databinding.FragmentBlogBinding
import com.example.smartdoctor.ui.blog.adapter.BlogAdapter
import com.example.smartdoctor.ui.chat.ChatAdapter
import com.example.smartdoctor.viewmodel.blog.BlogViewModel
import com.example.smartdoctor.viewmodel.chat.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BlogFragment : Fragment() {
    lateinit var binding:FragmentBlogBinding


    @Inject
    lateinit var blogAdapter: BlogAdapter

    private val viewModel: BlogViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlogBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //check connection and send request
        checkConnectionAndSendRequest()

        viewModel.blogPostList.observe(viewLifecycleOwner){
            blogAdapter.differ.submitList(it)
        }

        binding.apply {
            blogRecycler.apply {
                adapter = blogAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            }


        }

        blogAdapter.onItemClick ={
            val intent = Intent(context,BlogPostDetailActivity::class.java)
            intent.putExtra("title",it.title)
            intent.putExtra("text",it.text)
            requireContext().startActivity(intent)
        }

    }

    private fun checkConnectionAndSendRequest() {
        viewLifecycleOwner.lifecycleScope.launch{

            viewModel.loadBlogPostList()

        }
    }
}