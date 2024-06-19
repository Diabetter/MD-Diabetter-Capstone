package com.example.diabetter.view.main.ui.makanan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diabetter.R

class RecommendedFoodAdapter(private val itemCount: Int) : RecyclerView.Adapter<RecommendedFoodAdapter.FoodViewHolder>() {
    class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //TODO
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recommendation_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams

        holder.itemView.layoutParams = layoutParams
    }

    override fun getItemCount(): Int = itemCount

}