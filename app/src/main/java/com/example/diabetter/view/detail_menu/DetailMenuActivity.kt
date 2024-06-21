package com.example.diabetter.view.detail_menu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.diabetter.R
import com.example.diabetter.data.Result
import com.example.diabetter.data.remote.request.StoreMenuRequest
import com.example.diabetter.data.remote.response.FilteredDocsItem
import com.example.diabetter.data.remote.response.HistoryResponse
import com.example.diabetter.data.remote.response.MakananResponse
import com.example.diabetter.data.remote.response.PredictResponse
import com.example.diabetter.databinding.ActivityDetailFoodBinding
import com.example.diabetter.databinding.ActivityDetailMenuBinding
import com.example.diabetter.databinding.DetailMenuBinding
import com.example.diabetter.databinding.ToolbarPersonalizationBinding
import com.example.diabetter.utils.ObtainViewModelFactory
import com.example.diabetter.view.main.MainActivity
import java.math.BigDecimal
import java.math.RoundingMode

class DetailMenuActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailMenuBinding
    private lateinit var toolbarBinding : ToolbarPersonalizationBinding
    private lateinit var menuBinding : DetailMenuBinding
    private lateinit var viewModel : DetailMenuViewModel

    private lateinit var storeMenuRequest: StoreMenuRequest

    val staticUID = "GN2peLqjPWUIHTR4iWVX1lHrL3s1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbarBinding = ToolbarPersonalizationBinding.bind(binding.toolbar)
        menuBinding = DetailMenuBinding.bind(binding.detailMenu)
        viewModel = ObtainViewModelFactory.obtainViewModelFactory<DetailMenuViewModel>(this)

        toolbarBinding.backBtn.setOnClickListener { onBackPressed() }
        toolbarBinding.tvTitle.text = "Menu"
        val predictResponse: PredictResponse? = intent.getParcelableExtra("menu_item")
        val filteredDocsItem: FilteredDocsItem? = intent.getParcelableExtra("menu_history")
        val makananResponses: ArrayList<MakananResponse>? = intent.getParcelableArrayListExtra("makanan_responses")
        if (filteredDocsItem != null && makananResponses != null) {
            binding.btnSave.visibility = View.GONE
            bindHistoryData(filteredDocsItem, makananResponses)
        }else {
            val predictResponse: PredictResponse? = intent.getParcelableExtra("menu_item")
            if (predictResponse != null && makananResponses != null) {
                bindData(predictResponse, makananResponses)
            }
        }

        binding.btnSave.setOnClickListener {
            if (predictResponse != null) {
                storeMenuRequest = StoreMenuRequest(staticUID, predictResponse.data)
                Log.d("DetailMenuActivity", "StoreMenuRequest: $storeMenuRequest")
                viewModel.storeMenu(storeMenuRequest).observe(this) {result ->
                    if(result != null){
                        when(result){
                            is Result.Loading -> {
                                Log.d("DetailMenuActivity", "Loading")
                            }
                            is Result.Error -> {
                                Log.d("DetailMenuActivity", "Error: ${result.error}")
                            }
                            is Result.Success -> {
                                finish()
                                startActivity(Intent(this, MainActivity::class.java))
                            }
                        }
                    }
                }
            }

        }
    }

    private fun bindHistoryData(filteredDocsItem: FilteredDocsItem, makananResponses: ArrayList<MakananResponse>) {
        val result = filteredDocsItem

        val food1 = makananResponses.find { it.data.nama == result.food1 }
        val food2 = makananResponses.find { it.data.nama == result.food2 }
        val food3 = makananResponses.find { it.data.nama == result.food3 }

        Log.d("DetailMenuActivity", "Food1 URL: ${food1?.url}")
        Log.d("DetailMenuActivity", "Food2 URL: ${food2?.url}")
        Log.d("DetailMenuActivity", "Food3 URL: ${food3?.url}")

        menuBinding.apply {
            tvRating.text = BigDecimal(result.rating).setScale(2, RoundingMode.HALF_UP).toString()
            tvCalorie.text = result.kalori.toString()
            tvCarbo.text = result.karbohidrat.toString()
            tvProtein.text = result.protein.toString()
            tvFat.text = result.lemak.toString()
            rating.rating = result.rating.toFloat()
        }

        food1?.let {
            Glide.with(this).load(it.url).into(menuBinding.ivMenu1)
            Glide.with(this).load(it.url).into(binding.ivMenuDetail1)
            binding.tvFoodTitle1.text = it.data.nama
            binding.tvRating1.text = it.data.rating
            binding.tvCalorie1.text = it.data.kalori
            binding.tvCarbo1.text = it.data.karbohidrat
            binding.tvProtein1.text = it.data.protein
            binding.tvFat1.text = it.data.lemak
            binding.tvType1.text = it.data.jenis
        }
        food2?.let {
            Glide.with(this).load(it.url).into(menuBinding.ivMenu2)
            Glide.with(this).load(it.url).into(binding.ivMenuDetail2)
            binding.tvFoodTitle2.text = it.data.nama
            binding.tvRating2.text = it.data.rating
            binding.tvCalorie2.text = it.data.kalori
            binding.tvCarbo2.text = it.data.karbohidrat
            binding.tvProtein2.text = it.data.protein
            binding.tvFat2.text = it.data.lemak
            binding.tvType2.text = it.data.jenis
        }
        food3?.let {
            Glide.with(this).load(it.url).into(menuBinding.ivMenu3)
            Glide.with(this).load(it.url).into(binding.ivMenuDetail3)
            binding.tvFoodTitle3.text = it.data.nama
            binding.tvRating3.text = it.data.rating
            binding.tvCalorie3.text = it.data.kalori
            binding.tvCarbo3.text = it.data.karbohidrat
            binding.tvProtein3.text = it.data.protein
            binding.tvFat3.text = it.data.lemak
            binding.tvType3.text = it.data.jenis
        }
    }

    private fun bindData(predictResponse: PredictResponse, makananResponses: ArrayList<MakananResponse>) {
        val result = predictResponse.data

        val food1 = makananResponses.find { it.data.nama == result.food1 }
        val food2 = makananResponses.find { it.data.nama == result.food2 }
        val food3 = makananResponses.find { it.data.nama == result.food3 }

        Log.d("DetailMenuActivity", "Food1 URL: ${food1?.url}")
        Log.d("DetailMenuActivity", "Food2 URL: ${food2?.url}")
        Log.d("DetailMenuActivity", "Food3 URL: ${food3?.url}")

        menuBinding.apply {
            tvRating.text = BigDecimal(result.rating).setScale(2, RoundingMode.HALF_UP).toString()
            tvCalorie.text = result.kalori.toString()
            tvCarbo.text = result.karbohidrat.toString()
            tvProtein.text = result.protein.toString()
            tvFat.text = result.lemak.toString()
            rating.rating = result.rating.toFloat()
        }

        food1?.let {
            Glide.with(this).load(it.url).into(menuBinding.ivMenu1)
            Glide.with(this).load(it.url).into(binding.ivMenuDetail1)
            binding.tvFoodTitle1.text = it.data.nama
            binding.tvRating1.text = it.data.rating
            binding.tvCalorie1.text = it.data.kalori
            binding.tvCarbo1.text = it.data.karbohidrat
            binding.tvProtein1.text = it.data.protein
            binding.tvFat1.text = it.data.lemak
            binding.tvType1.text = it.data.jenis
        }
        food2?.let {
            Glide.with(this).load(it.url).into(menuBinding.ivMenu2)
            Glide.with(this).load(it.url).into(binding.ivMenuDetail2)
            binding.tvFoodTitle2.text = it.data.nama
            binding.tvRating2.text = it.data.rating
            binding.tvCalorie2.text = it.data.kalori
            binding.tvCarbo2.text = it.data.karbohidrat
            binding.tvProtein2.text = it.data.protein
            binding.tvFat2.text = it.data.lemak
            binding.tvType2.text = it.data.jenis
        }
        food3?.let {
            Glide.with(this).load(it.url).into(menuBinding.ivMenu3)
            Glide.with(this).load(it.url).into(binding.ivMenuDetail3)
            binding.tvFoodTitle3.text = it.data.nama
            binding.tvRating3.text = it.data.rating
            binding.tvCalorie3.text = it.data.kalori
            binding.tvCarbo3.text = it.data.karbohidrat
            binding.tvProtein3.text = it.data.protein
            binding.tvFat3.text = it.data.lemak
            binding.tvType3.text = it.data.jenis
        }
    }
}