package com.example.diabetter.view.main.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diabetter.R
import com.example.diabetter.databinding.FragmentHomeBinding
import com.example.diabetter.databinding.TodayMenuBinding
import com.example.diabetter.utils.StatusBar
import com.example.diabetter.view.detail_menu.DetailMenuActivity
import com.example.diabetter.adapter.RecommendationMenuAdapter
import com.example.diabetter.adapter.setupRecyclerView
import com.example.diabetter.view.main.RefreshFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var menuTodayMenuBinding: TodayMenuBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuTodayMenuBinding = binding.menuToday
        StatusBar.addStatusBarMargin(requireActivity(), binding.tvHello, 32)
        StatusBar.addStatusBarMargin(requireActivity(), binding.ivSave, 32)
//        val ratingBar = view.findViewById<RatingBar>(R.id.rating)
//
//        // Atur nilai rating
//        ratingBar.rating = 3.65f

        binding.rvRecommendationMenu.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecommendationMenu.adapter = RecommendationMenuAdapter(5)

        menuTodayMenuBinding.apply {
            tvSeeDetail.setOnClickListener {
                val intent = Intent(requireContext(), DetailMenuActivity::class.java)
                startActivity(intent)
            }
            tvDetailMenuToday.setOnClickListener {
                val intent = Intent(requireContext(), DetailMenuActivity::class.java)
                startActivity(intent)
            }
        }

        binding.tvRefresh2.setOnClickListener {
            val showPopUpRefresh = RefreshFragment()
            showPopUpRefresh.show((activity as AppCompatActivity).supportFragmentManager, "RefreshFragment")
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_recommendation_menu)
        setupRecyclerView(recyclerView, MENU_RECOMMENDATION_COUNT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val MENU_RECOMMENDATION_COUNT = 5
    }
}