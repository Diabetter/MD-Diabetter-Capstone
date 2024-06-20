package com.example.diabetter.data.api.user.profile.create


import com.google.gson.annotations.SerializedName

data class CreateUserProfileResponse(
    @SerializedName("message")
    val message: String
)