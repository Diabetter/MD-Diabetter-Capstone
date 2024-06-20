package com.example.diabetter.data.api.user.profile


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserProfileResponse(
    @SerializedName("activities")
    val activities: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("food_id")
    val foodId: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("weight")
    val weight: Int
) : Parcelable