package com.example.diabetter.view.main.ui.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diabetter.data.remote.response.DocsItem
import com.example.diabetter.data.remote.response.MakananResponse
import com.example.diabetter.databinding.WithRatingMenuBinding
import java.math.BigDecimal
import java.math.RoundingMode

class PopularMenuAdapter(private var data: List<DocsItem>) : RecyclerView.Adapter<PopularMenuAdapter.MenuViewHolder>() {

    private var makananResponses: List<MakananResponse> = emptyList()

    fun setData(docsItems: List<DocsItem>) {
        data = docsItems
        notifyDataSetChanged()
    }

    fun addMakananResponse(makananResponse: MakananResponse) {
        makananResponses += makananResponse
        notifyDataSetChanged()
    }

    fun setMakananResponses(makananResponses: List<MakananResponse>) {
        this.makananResponses = makananResponses
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = WithRatingMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val docsItem = data[position]
        holder.bind(docsItem, makananResponses)
    }

    inner class MenuViewHolder(private val binding: WithRatingMenuBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(docsItem: DocsItem, makananResponses: List<MakananResponse>) {
            binding.apply {
                tvCalLevel.text = docsItem.kalori.toString()
                textView6.text = BigDecimal(docsItem.rating ?: 0.0).setScale(2, RoundingMode.HALF_UP).toString()
                ratingBar.rating = docsItem.rating?.toFloat() ?: 0f

                val makananResponse1 = makananResponses.find { it.data.nama == docsItem.food1 }
                makananResponse1?.let {
                    Glide.with(itemView.context)
                        .load(makananResponse1.url)
                        .into(ivMenu1)
                }

                val makananResponse2 = makananResponses.find { it.data.nama == docsItem.food2 }
                makananResponse2?.let {
                    Glide.with(itemView.context)
                        .load(makananResponse2.url)
                        .into(ivMenu2)
                }

                val makananResponse3 = makananResponses.find { it.data.nama == docsItem.food3 }
                makananResponse3?.let {
                    Glide.with(itemView.context)
                        .load(makananResponse3.url)
                        .into(ivMenu3)
                }
            }
        }
    }
}