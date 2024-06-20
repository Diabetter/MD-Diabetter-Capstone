package com.example.diabetter.data.api.user.profile.edit


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EditUserProfileResponse(
    @SerializedName("message")
    val message: String
): Parcelable