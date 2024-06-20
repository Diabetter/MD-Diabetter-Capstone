package com.example.diabetter.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diabetter.R
import com.example.diabetter.data.remote.response.MakananResponse
import com.example.diabetter.data.remote.response.PredictResponse
import com.example.diabetter.databinding.RecommendationMenuBinding
import com.example.diabetter.view.detail_menu.DetailMenuActivity
import java.math.BigDecimal
import java.math.RoundingMode

class RecommendationMenuAdapter(private val items: List<PredictResponse>, private val makananResponses: List<MakananResponse>) :
    RecyclerView.Adapter<RecommendationMenuAdapter.ViewHolder>() {

    class ViewHolder(private val binding: RecommendationMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(predictResponse: PredictResponse, makananResponses: List<MakananResponse>) {
            val result = predictResponse.data
            binding.tvRating.text = BigDecimal(result.rating).setScale(2, RoundingMode.HALF_UP).toString()
            binding.tvSugarLevel.text = result.kalori.toString()
            binding.rating.rating = result.rating.toFloat()

            val food1 = makananResponses.find { it.data.nama == result.food1 }
            val food2 = makananResponses.find { it.data.nama == result.food2 }
            val food3 = makananResponses.find { it.data.nama == result.food3 }

            food1?.let {
                Glide.with(binding.ivMenu1.context).load(it.url).into(binding.ivMenu1)
            }
            food2?.let {
                Glide.with(binding.ivMenu2.context).load(it.url).into(binding.ivMenu2)
            }
            food3?.let {
                Glide.with(binding.ivMenu3.context).load(it.url).into(binding.ivMenu3)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecommendationMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
        val marginFirst = holder.itemView.resources.getDimensionPixelSize(R.dimen.margin_first_item)
        val marginLast = holder.itemView.resources.getDimensionPixelSize(R.dimen.margin_last_item)

        when (position) {
            0 -> layoutParams.leftMargin = marginFirst
            itemCount - 1 -> layoutParams.rightMargin = marginLast
            else -> {}
        }
        holder.itemView.layoutParams = layoutParams


        holder.bind(items[position], makananResponses)

        holder.itemView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, DetailMenuActivity::class.java)

            intent.putExtra("menu_item", item)
            intent.putParcelableArrayListExtra("makanan_responses", ArrayList(makananResponses))
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = items.size
}

fun setupRecyclerView(
    recyclerView: RecyclerView,
    predictResponses: List<PredictResponse>,
    makananResponses: List<MakananResponse>,
    onMostVisibleItemChanged: (Int) -> Unit
) {
    val linearLayoutManager =
        LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
    recyclerView.layoutManager = linearLayoutManager
    recyclerView.adapter = RecommendationMenuAdapter(predictResponses, makananResponses)

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            findMostVisibleItem(recyclerView, linearLayoutManager, onMostVisibleItemChanged)
        }
    }

    recyclerView.addOnScrollListener(scrollListener)

    recyclerView.viewTreeObserver.addOnGlobalLayoutListener {
        findMostVisibleItem(recyclerView, linearLayoutManager, onMostVisibleItemChanged)
    }
}

fun findMostVisibleItem(
    recyclerView: RecyclerView,
    layoutManager: LinearLayoutManager,
    onMostVisibleItemChanged: (Int) -> Unit
) {
    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

    var maxVisibleWidth = 0
    var mostVisibleItemPosition = -1
    var lastMostVisibleItemPosition = -1

    for (i in firstVisibleItemPosition..lastVisibleItemPosition) {
        val view = layoutManager.findViewByPosition(i) ?: continue

        val leftVisibleWidth = if (view.left >= 0) view.width else view.width + view.left
        val rightVisibleWidth =
            if (view.right <= recyclerView.width) view.width else recyclerView.width - view.left
        val visibleWidth = minOf(leftVisibleWidth, rightVisibleWidth)

        if (visibleWidth > maxVisibleWidth) {
            maxVisibleWidth = visibleWidth
            mostVisibleItemPosition = i
        }
    }

    if (mostVisibleItemPosition != -1 && mostVisibleItemPosition != lastMostVisibleItemPosition) {
        lastMostVisibleItemPosition = mostVisibleItemPosition
        Log.d("RecommendationMenuAdapter", "Most visible item position: $mostVisibleItemPosition")
        onMostVisibleItemChanged(mostVisibleItemPosition)
    }
}
