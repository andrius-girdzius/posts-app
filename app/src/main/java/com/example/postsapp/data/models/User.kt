package com.example.postsapp.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var name: String,
    var username: String,
    var email: String,
    @Embedded
    var address: Address,
    var phone: String,
    var website: String,
    @Embedded
    var company: Company,
)