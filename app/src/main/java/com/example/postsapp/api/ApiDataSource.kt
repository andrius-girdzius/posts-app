package com.example.postsapp.api

import javax.inject.Inject

class ApiDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getPosts() = apiService.getPosts()
    suspend fun getUser(id: Int) = apiService.getUser(id)
}