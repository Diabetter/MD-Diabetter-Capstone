package com.example.diabetter.view.history

import android.view.Menu
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.diabetter.data.Result
import com.example.diabetter.data.remote.request.GetMakananRequest
import com.example.diabetter.data.remote.request.UserHistoryRequest
import com.example.diabetter.data.remote.response.HistoryResponse
import com.example.diabetter.data.remote.response.MakananResponse
import com.example.diabetter.data.repository.MenuRepository

class HistoryViewModel (private val menuRepository: MenuRepository) : ViewModel() {
    fun getHistory(uid : UserHistoryRequest) : LiveData<Result<HistoryResponse>> = menuRepository.getHistory(uid)

    fun getMakanan(
        namaMakanan : GetMakananRequest
    ) : LiveData<Result<MakananResponse>>{
        return menuRepository.getMakanan(namaMakanan)
    }
}