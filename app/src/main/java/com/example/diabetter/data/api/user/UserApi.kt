package com.example.diabetter.data.api.user

import com.example.diabetter.data.api.user.login.LoginRequest
import com.example.diabetter.data.api.user.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("login")
    fun login (
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>
}