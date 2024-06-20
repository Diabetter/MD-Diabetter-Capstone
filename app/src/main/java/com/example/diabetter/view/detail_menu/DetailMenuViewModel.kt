package com.example.diabetter.view.detail_menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.diabetter.data.Result
import com.example.diabetter.data.remote.request.StoreMenuRequest
import com.example.diabetter.data.remote.response.StoreMenuResponse
import com.example.diabetter.data.repository.MenuRepository

class DetailMenuViewModel (private val menuRepository: MenuRepository) : ViewModel() {
    fun storeMenu(menu : StoreMenuRequest) : LiveData<Result<StoreMenuResponse>>{
        return menuRepository.storeMenu(menu)
    }
}