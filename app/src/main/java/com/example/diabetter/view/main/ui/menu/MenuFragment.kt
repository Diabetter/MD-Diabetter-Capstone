package com.example.diabetter.view.main.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diabetter.R
import com.example.diabetter.databinding.FragmentMenuBinding
import com.example.diabetter.view.main.ui.menu.adapter.PopularMenuAdapter

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_popmenu)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = PopularMenuAdapter(5)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}