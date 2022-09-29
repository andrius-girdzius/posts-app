package com.example.postsapp.ui.postList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.postsapp.data.AppRepository
import com.example.postsapp.data.models.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(val repository: AppRepository) : ViewModel() {
    lateinit var posts: LiveData<List<Post>>

    init {
        try {
            posts = repository.getPosts()
        } catch (e: Exception) {
        }
    }
}