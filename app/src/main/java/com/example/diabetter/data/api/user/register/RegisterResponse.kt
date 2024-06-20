package com.example.diabetter.data.api.user.register


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("uid")
    val uid: String
)