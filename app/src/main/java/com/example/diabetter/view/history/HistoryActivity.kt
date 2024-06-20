package com.example.diabetter.view.history

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diabetter.R
import com.example.diabetter.adapter.HistoryMenuAdapter
import com.example.diabetter.data.Result
import com.example.diabetter.data.remote.request.GetMakananRequest
import com.example.diabetter.data.remote.request.UserHistoryRequest
import com.example.diabetter.data.remote.response.MakananResponse
import com.example.diabetter.databinding.ActivityHistoryBinding
import com.example.diabetter.utils.ObtainViewModelFactory

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHistoryBinding
    private lateinit var viewModel : HistoryViewModel

    private lateinit var adapter: HistoryMenuAdapter
    private var makananResponses = mutableListOf<MakananResponse>()

    val staticUID = "GN2peLqjPWUIHTR4iWVX1lHrL3s1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupRecyclerView()

        binding.backBtn.setOnClickListener { onBackPressed() }

        viewModel = ObtainViewModelFactory.obtainViewModelFactory<HistoryViewModel>(this)

        val userHistoryRequest = UserHistoryRequest(uid = staticUID)
        viewModel.getHistory(userHistoryRequest).observe(this) {result ->
            if (result != null) {
                when(result){
                    is Result.Loading -> {
                        Log.d("Testt", "Loading")
                    }
                    is Result.Error -> {
                        Log.d("Testt", result.error.toString())
                    }
                    is Result.Success -> {
                        val historyList = result.data.filteredDocs
                        makananResponses.clear()

                        val foodNames = historyList.flatMap { doc ->
                            listOf(doc.food1, doc.food2, doc.food3)
                        }
                        foodNames.forEach { foodName ->
                            val getMakananRequest = GetMakananRequest(foodName)
                            viewModel.getMakanan(getMakananRequest).observe(this) { result ->
                                when (result) {
                                    is Result.Loading -> {}
                                    is Result.Error -> {
                                        Log.e("HistoryActivity", "Error: ${result.error}")
                                    }
                                    is Result.Success -> {
                                        val makananResponse = result.data
                                        makananResponses.add(makananResponse)
                                        if (makananResponses.size == foodNames.size) {
                                            adapter.notifyDataSetChanged()
                                        }
                                        adapter = HistoryMenuAdapter(historyList, makananResponses)
                                        binding.rvHistory.adapter = adapter
//                        binding.progress.visibility = View.GONE
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    private fun setupRecyclerView() {
        adapter = HistoryMenuAdapter(emptyList(), makananResponses)
        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        binding.rvHistory.adapter = adapter
    }

}