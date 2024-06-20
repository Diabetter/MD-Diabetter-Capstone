package com.example.diabetter.data.api.user.profile.edit


import com.google.gson.annotations.SerializedName

data class EditUserProfileRequest(
    @SerializedName("activities")
    val activities: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("weight")
    val weight: Int
)