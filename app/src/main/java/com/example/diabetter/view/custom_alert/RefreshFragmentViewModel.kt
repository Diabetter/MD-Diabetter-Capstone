package com.example.diabetter.view.custom_alert

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.diabetter.data.Result
import com.example.diabetter.data.remote.request.GetMakananRequest
import com.example.diabetter.data.remote.response.MakananResponse
import com.example.diabetter.data.remote.response.PredictResponse
import com.example.diabetter.data.repository.MenuRepository

class RefreshFragmentViewModel(private val menuRepository: MenuRepository) : ViewModel() {
    fun predict(
        uid : String,
        rating : Int
    ) : LiveData<Result<List<PredictResponse>>> {
        return menuRepository.predict(uid, rating)
    }

    fun getMakanan(
        namaMakanan : GetMakananRequest
    ) : LiveData<Result<MakananResponse>> {
        return menuRepository.getMakanan(namaMakanan)
    }
}