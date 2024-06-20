package com.example.diabetter.view.main.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diabetter.data.Result
import com.example.diabetter.data.remote.request.GetMakananRequest
import com.example.diabetter.data.remote.response.AllHistoryResponse
import com.example.diabetter.data.remote.response.MakananResponse
import com.example.diabetter.data.repository.MenuRepository

class DashboardViewModel(private val menuRepository: MenuRepository) : ViewModel() {
    fun getAllHistory() : LiveData<Result<AllHistoryResponse>> {
        return menuRepository.getAllHistory()
    }

    fun getMakanan(
        namaMakanan : GetMakananRequest
    ) : LiveData<Result<MakananResponse>>{
        return menuRepository.getMakanan(namaMakanan)
    }
}