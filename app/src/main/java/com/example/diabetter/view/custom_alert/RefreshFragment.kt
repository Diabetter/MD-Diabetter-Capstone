package com.example.diabetter.view.custom_alert

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.diabetter.R
import com.example.diabetter.adapter.setupRecyclerView
import com.example.diabetter.data.Result
import com.example.diabetter.data.remote.request.GetMakananRequest
import com.example.diabetter.data.remote.response.MakananResponse
import com.example.diabetter.data.remote.response.PredictResponse
import com.example.diabetter.databinding.FragmentHomeBinding
import com.example.diabetter.databinding.FragmentRefreshBinding
import com.example.diabetter.utils.ObtainViewModelFactory
import com.example.diabetter.view.main.ui.home.HomeViewModel

class RefreshFragment : DialogFragment() {
    private var _binding: FragmentRefreshBinding? = null
    private lateinit var viewModel: RefreshFragmentViewModel
    private val binding get() = _binding!!

    private var _homeBinding: FragmentHomeBinding? = null
    private val homeBinding get() = _homeBinding!!

    val staticUID = "GN2peLqjPWUIHTR4iWVX1lHrL3s1"
    private var predictResponses: List<PredictResponse> = emptyList()
    private var makananResponses: List<MakananResponse> = emptyList()

    private var completedRequests = 0
    private var rating = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.custom_dialog);
        viewModel = ObtainViewModelFactory.obtainViewModelFactory<RefreshFragmentViewModel>(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRefreshBinding.inflate(inflater, container, false)
        _homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding

        binding.btnCancel.setOnClickListener {dismiss()}

        val radioGroup = binding.rgRating
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb0 -> {
                    rating = 1
                }
                R.id.rb1 -> {
                    rating = 1
                }
                R.id.rb2 -> {
                    rating = 2
                }
                R.id.rb3 -> {
                    rating = 3
                }
                R.id.rb4 -> {
                    rating = 4
                }
                else -> ""
            }
        }

        binding.btnRefresh.setOnClickListener {
            viewModel.predict(staticUID, rating).observe(viewLifecycleOwner){result ->
                if(result != null){
                    when(result){
                        is Result.Loading -> {
                            binding.progressRefresh.visibility = View.VISIBLE
                        }
                        is Result.Error -> {
                            binding.progressRefresh.visibility = View.VISIBLE
                            Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT).show()
                        }
                        is Result.Success -> {
                            val data = result.data
                            Log.d("Testt", data.toString())
                            predictResponses = data

                            val foodNames = predictResponses.flatMap {
                                listOf(it.data.food1, it.data.food2, it.data.food3)
                            }

                            completedRequests = 0
                            makananResponses = emptyList()

                            foodNames.forEach { foodName ->
                                val getMakananRequest = GetMakananRequest(foodName)
//                                Log.d("Testt", getMakananRequest.toString())
                                viewModel.getMakanan(getMakananRequest).observe(viewLifecycleOwner) { result ->
                                    when (result) {
                                        is Result.Loading -> {
                                            binding.progressRefresh.visibility = View.VISIBLE
                                        }
                                        is Result.Error -> {
                                            Log.e("HomeFragment", "Error: ${result.error}")
                                        }
                                        is Result.Success -> {
                                            val makananResponse = result.data
                                            makananResponses = makananResponses + makananResponse
                                            completedRequests++
//                                            Log.d("Testt", makananResponses.toString())
                                            if (makananResponses.size == foodNames.size) {
                                                setupRecyclerView()
                                                binding.progressRefresh.visibility = View.GONE
                                                dismiss()
                                            }
                                        }
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
        setupRecyclerView(homeBinding.rvRecommendationMenu, predictResponses, makananResponses) { mostVisibleItemPosition ->
            homeBinding.apply {
                circle1.setImageResource(
                    if (mostVisibleItemPosition == 0) R.drawable.active_circle
                    else R.drawable.inactive_circle
                )
                circle1.requestLayout()

                circle2.setImageResource(
                    if (mostVisibleItemPosition == 1) R.drawable.active_circle
                    else R.drawable.inactive_circle
                )
                circle2.requestLayout()

                circle3.setImageResource(
                    if (mostVisibleItemPosition == 2) R.drawable.active_circle
                    else R.drawable.inactive_circle
                )
                circle3.requestLayout()

                circle4.setImageResource(
                    if (mostVisibleItemPosition == 3) R.drawable.active_circle
                    else R.drawable.inactive_circle
                )
                circle4.requestLayout()

                circle5.setImageResource(
                    if (mostVisibleItemPosition == 4) R.drawable.active_circle
                    else R.drawable.inactive_circle
                )
                circle5.requestLayout()
            }
        }
    }
}