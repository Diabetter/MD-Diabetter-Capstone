package com.example.diabetter.view.main.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diabetter.R

class RecommendationMenuAdapter(private val itemCount: Int) :
    RecyclerView.Adapter<RecommendationMenuAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Tambahkan referensi ke view di layout recommendation_menu.xml
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recommendation_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
        val marginFirst = holder.itemView.resources.getDimensionPixelSize(R.dimen.margin_first_item)
        val marginLast = holder.itemView.resources.getDimensionPixelSize(R.dimen.margin_last_item)

        when (position) {
            0 -> layoutParams.leftMargin = marginFirst
            itemCount - 1 -> layoutParams.rightMargin = marginLast
            else -> {}
        }
        holder.itemView.layoutParams = layoutParams
    }

    override fun getItemCount() = itemCount
}
