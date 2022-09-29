package com.example.postsapp.data.models

import androidx.room.Embedded

data class Address(
    var street: String,
    var suite: String,
    var city: String,
    var zipcode: String,
    @Embedded
    var geo: Geo
)