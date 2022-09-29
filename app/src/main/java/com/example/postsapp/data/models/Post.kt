package com.example.postsapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var userId: Int,
    var title: String,
    var body: String,
)