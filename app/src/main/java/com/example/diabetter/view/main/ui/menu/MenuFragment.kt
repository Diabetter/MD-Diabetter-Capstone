package com.example.diabetter.view.main.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diabetter.R
import com.example.diabetter.databinding.FragmentMenuBinding
import com.example.diabetter.data.Result
import com.example.diabetter.data.remote.request.GetMakananRequest
import com.example.diabetter.data.remote.response.DocsItem
import com.example.diabetter.data.remote.response.MakananResponse
import com.example.diabetter.utils.ObtainViewModelFactory
import com.example.diabetter.view.main.ui.menu.adapter.PopularMenuAdapter

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuViewModel: MenuViewModel
    private lateinit var popularMenuAdapter: PopularMenuAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        menuViewModel = ObtainViewModelFactory.obtainViewModelFactory(requireContext())

        val recyclerView = binding?.rvPopmenu
        recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        popularMenuAdapter = PopularMenuAdapter(emptyList())
        recyclerView?.adapter = popularMenuAdapter


        // Observing all history
        menuViewModel.getAllHistory().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    val allHistoryResponse = result.data
                    allHistoryResponse?.let {
                        popularMenuAdapter.setData(it.docs)
                        val foodNames = it.docs.flatMap { listOf(it.food1, it.food2, it.food3) }.distinct()
                        fetchMakananResponses(foodNames)
                    }
                }
                is Result.Error -> {
                }
                is Result.Loading -> {
                }
            }
        })

        return view
    }

    private fun fetchMakananResponses(foodNames: List<String>) {
        foodNames.forEach { foodName ->
            val getMakananRequest = GetMakananRequest(foodName)
            menuViewModel.getMakanan(getMakananRequest).observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Result.Success -> {
                        val makananResponse = result.data
                        popularMenuAdapter.addMakananResponse(makananResponse)
                    }
                    is Result.Error -> {
                    }
                    is Result.Loading -> {
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}