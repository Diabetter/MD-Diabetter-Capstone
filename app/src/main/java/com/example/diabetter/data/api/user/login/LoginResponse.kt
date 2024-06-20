package com.example.diabetter.data.api.user.login


import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("uid")
    val uid: String
)