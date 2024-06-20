package com.example.diabetter.data.remote.retrofit

import com.example.diabetter.data.remote.request.GetMakananRequest
import com.example.diabetter.data.remote.request.PredictRequest
import com.example.diabetter.data.remote.request.StoreMenuRequest
import com.example.diabetter.data.remote.response.MakananResponse
import com.example.diabetter.data.remote.response.PredictResponse
import com.example.diabetter.data.remote.response.StoreMenuResponse
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("api/temp-predict")
    suspend fun predict(
        @Body body: PredictRequest
    ): PredictResponse

    @POST("api/get-makanan")
    suspend fun getMakanan(
        @Body body: GetMakananRequest
    ): MakananResponse

    @POST("api/store-predict")
    suspend fun storePredict(
        @Body body: StoreMenuRequest
    ): StoreMenuResponse
}