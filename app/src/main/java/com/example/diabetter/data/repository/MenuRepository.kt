package com.example.diabetter.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.diabetter.data.Result
import com.example.diabetter.data.api.FoodApi
import com.example.diabetter.data.preference.LoginPreferences
import com.example.diabetter.data.remote.request.GetMakananRequest
import com.example.diabetter.data.remote.request.PredictRequest
import com.example.diabetter.data.remote.request.StoreMenuRequest
import com.example.diabetter.data.remote.request.UserHistoryRequest
import com.example.diabetter.data.remote.response.AllHistoryResponse
import com.example.diabetter.data.remote.response.HistoryResponse
import com.example.diabetter.data.remote.response.MakananResponse
import com.example.diabetter.data.remote.response.PredictResponse
import com.example.diabetter.data.remote.response.StoreMenuResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import retrofit2.HttpException

class MenuRepository(
    private var apiService : FoodApi,
) {
    fun predict(
        uid: String,
        rating: Int
    ): LiveData<Result<List<PredictResponse>>> = liveData {
        emit(Result.Loading)
        try {
            val responses = coroutineScope {
                val deferredResponses = List(5) {
                    async {
                        val requestBody = PredictRequest(uid, rating)
                        apiService.predict(requestBody)
                    }
                }
                deferredResponses.awaitAll()
            }
            emit(Result.Success(responses))
        } catch (e: HttpException) {
            emit(Result.Error(e.message.toString()))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getMakanan(
        namaMakanan : GetMakananRequest
    ) : LiveData<Result<MakananResponse>> = liveData{
        emit(Result.Loading)
        try{
            val response = apiService.getMakanan(namaMakanan)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            emit(Result.Error(e.message.toString()))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }

    }

    fun storeMenu(
        menu : StoreMenuRequest
    ) : LiveData<Result<StoreMenuResponse>> = liveData{
        emit(Result.Loading)
        try {
            val response = apiService.storePredict(menu)
            emit(Result.Success(response))
        }catch (e: HttpException) {
            emit(Result.Error(e.message.toString()))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }

    }

    fun getHistory(
        uid : UserHistoryRequest
    ) : LiveData<Result<HistoryResponse>> = liveData{
        emit(Result.Loading)
        try {
            val response = apiService.getHistory(uid)
            emit(Result.Success(response))
        }catch (e: HttpException) {
            emit(Result.Error(e.message.toString()))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getAllHistory() : LiveData<Result<AllHistoryResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllHistory()
            emit(Result.Success(response))
        }catch (e: HttpException) {
            emit(Result.Error(e.message.toString()))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
    companion object {
        @Volatile
        private var instance: MenuRepository? = null

        fun getInstance(
            apiService: FoodApi,

            ): MenuRepository =
            instance ?: synchronized(this) {
                instance ?: MenuRepository(
                    apiService
                ).also {
                    instance = it
                }
            }
    }
}