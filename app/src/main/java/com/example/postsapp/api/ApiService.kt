package com.example.postsapp.api

import com.example.postsapp.data.models.Post
import com.example.postsapp.data.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): User
}

