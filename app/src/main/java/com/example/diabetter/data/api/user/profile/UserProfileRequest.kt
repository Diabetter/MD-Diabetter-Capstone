package com.example.diabetter.data.api.user.profile


import com.google.gson.annotations.SerializedName

data class UserProfileRequest(
    @SerializedName("userid")
    val userid: String
)