package com.example.diabetter.view.main.ui.makanan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diabetter.R
import com.example.diabetter.databinding.FragmentNotificationsBinding
import com.example.diabetter.utils.GridSpacingItemDecoration
import com.example.diabetter.view.main.ui.makanan.adapter.RecommendedFoodAdapter

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_foodRecom)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        val spacingInDp = 10
        val itemDecoration = GridSpacingItemDecoration(2, dpToPX(spacingInDp))
        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.adapter = RecommendedFoodAdapter(10)

        return view
    }

    private fun dpToPX(dp: Int): Int {
        val density = resources.displayMetrics.density
        return Math.round(dp * density)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}