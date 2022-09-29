package com.example.postsapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.postsapp.api.ApiDataSource
import com.example.postsapp.data.models.Post
import com.example.postsapp.data.models.User
import com.example.postsapp.utils.ApiException
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val postsDao: PostsDao,
    private val usersDao: UsersDao,
    private val dataSource: ApiDataSource
) {
    fun getPosts(): LiveData<List<Post>> {
        return liveData {
            emitSource(postsDao.getAllPosts())
            try {
                val newPosts = dataSource.getPosts()
                postsDao.savePosts(newPosts)
            } catch (e: Exception) {
                throw ApiException()
            }
        }
    }

    fun getUser(id: Int): LiveData<List<User>> {
        return liveData {
            emitSource(usersDao.getUserById(id))
            try {
                val newUser = dataSource.getUser(id)
                usersDao.saveUser(newUser)
            } catch (e: Exception) {
                throw ApiException()
            }
        }
    }
}