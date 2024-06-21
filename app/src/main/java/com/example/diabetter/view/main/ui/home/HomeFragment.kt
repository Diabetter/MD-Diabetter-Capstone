package com.example.diabetter.view.main.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diabetter.R
import com.example.diabetter.databinding.FragmentHomeBinding
import com.example.diabetter.databinding.TodayMenuBinding
import com.example.diabetter.view.detail_menu.DetailMenuActivity
import com.example.diabetter.adapter.RecommendationMenuAdapter
import com.example.diabetter.adapter.setupRecyclerView
import com.example.diabetter.data.MenuData
import com.example.diabetter.data.Result
import com.example.diabetter.data.preference.LoginPreferences
import com.example.diabetter.data.remote.request.GetMakananRequest
import com.example.diabetter.data.remote.response.MakananResponse
import com.example.diabetter.data.remote.response.PredictResponse
import com.example.diabetter.databinding.OtherFoodBinding
import com.example.diabetter.utils.ObtainViewModelFactory
import com.example.diabetter.view.custom_alert.RefreshFragment
import com.example.diabetter.view.detail_food.DetailFoodActivity
import com.example.diabetter.view.detail_menu_today.DetailMenuTodayActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var menuTodayMenuBinding: TodayMenuBinding

    private val loginPreferences: LoginPreferences by lazy {
        LoginPreferences(requireContext())
    }
    private var predictResponses: List<PredictResponse> = emptyList()
    private var makananResponses: List<MakananResponse> = emptyList()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ObtainViewModelFactory.obtainViewModelFactory<HomeViewModel>(requireContext())

        loginPreferences.retrieveUid()?.let {
            viewModel.predict(it, 1).observe(requireActivity()){ result ->
                if(result != null){
                    when(result){
                        is Result.Loading -> {
                            binding?.progress?.visibility ?:  View.VISIBLE
                        }

                        is Result.Error -> {
                            binding?.progress?.visibility ?:  View.VISIBLE
                            Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT).show()
                        }

                        is Result.Success -> {
                            val data = result.data
                            Log.d("Testt", data.toString())
                            predictResponses = data

                            val foodNames = predictResponses.flatMap {
                                listOf(it.data.food1, it.data.food2, it.data.food3)
                            }

                            foodNames.forEach { foodName ->
                                val getMakananRequest = GetMakananRequest(foodName)
                                viewModel.getMakanan(getMakananRequest).observe(this) { result ->
                                    when (result) {
                                        is Result.Loading -> {
                                            binding?.progress?.visibility ?:  View.VISIBLE
                                        }

                                        is Result.Error -> {
                                            Log.e("HomeFragment", "Error: ${result.error}")
                                        }

                                        is Result.Success -> {
                                            val makananResponse = result.data
                                            makananResponses = makananResponses + makananResponse
                                            if (makananResponses.size == foodNames.size) {
                                                setupRecyclerView()
                                            }
                                            binding?.progress?.visibility ?:  View.GONE
    //                                        Log.d("Testt", makananResponses.toString())
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding?.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding

        if (binding != null) {
            menuTodayMenuBinding = binding.menuToday
        }

        menuTodayMenuBinding.apply {
            listOf(tvSeeDetail, tvDetailMenuToday).forEach { tv ->
                tv.setOnClickListener {
                    val intent = Intent(requireContext(), DetailMenuTodayActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        if (binding != null) {
            binding.tvRefresh2.setOnClickListener {
                val showPopUpRefresh = RefreshFragment()
                showPopUpRefresh.show((activity as AppCompatActivity).supportFragmentManager, "RefreshFragment")
            }
        }

        if (predictResponses.isNotEmpty()) {
            setupRecyclerView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("Testt", "Destroy")
    }

    private fun setupRecyclerView() {
        val binding = _binding ?: return

        setupRecyclerView(binding.rvRecommendationMenu, predictResponses, makananResponses) { mostVisibleItemPosition ->
            binding.apply {
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