package com.example.diabetter.data.api.user

import com.example.diabetter.data.api.user.login.LoginRequest
import com.example.diabetter.data.api.user.login.LoginResponse
import com.example.diabetter.data.api.user.profile.UserProfileRequest
import com.example.diabetter.data.api.user.profile.UserProfileResponse
import com.example.diabetter.data.api.user.profile.create.CreateUserProfileRequest
import com.example.diabetter.data.api.user.profile.create.CreateUserProfileResponse
import com.example.diabetter.data.api.user.profile.edit.EditUserProfileRequest
import com.example.diabetter.data.api.user.profile.edit.EditUserProfileResponse
import com.example.diabetter.data.api.user.register.RegisterRequest
import com.example.diabetter.data.api.user.register.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("login")
    fun login (
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    @POST("register")
    fun register(
        @Body registerRequest: RegisterRequest
    ): Call<RegisterResponse>

    @POST("get-profile")
    fun getProfile(
        @Body userProfileRequest: UserProfileRequest
    ): Call<UserProfileResponse>

    @POST("create-profile")
    fun createProfile(
        @Body createUserProfileRequest: CreateUserProfileRequest
    ): Call<CreateUserProfileResponse>

    @POST("edit-profile")
    fun editProfile(
        @Body editUserProfileRequest: EditUserProfileRequest
    ): Call<EditUserProfileResponse>
}