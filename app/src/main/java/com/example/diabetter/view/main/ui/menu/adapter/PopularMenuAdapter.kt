package com.example.diabetter.view.main.ui.menu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diabetter.R

class PopularMenuAdapter(private val itemCount: Int) : RecyclerView.Adapter<PopularMenuAdapter.MenuViewHolder>() {

    class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //TODO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.with_rating_menu, parent, false)
        return  MenuViewHolder(view)
    }

    override fun getItemCount(): Int = itemCount

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams

        holder.itemView.layoutParams = layoutParams
    }
}