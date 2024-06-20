package com.example.diabetter.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diabetter.data.remote.response.FilteredDocsItem
import com.example.diabetter.data.remote.response.MakananResponse
import com.example.diabetter.databinding.HistoryMenuBinding
import com.example.diabetter.view.detail_menu.DetailMenuActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryMenuAdapter(
    private val items: List<FilteredDocsItem>,
    private val makananResponses: List<MakananResponse>
) : RecyclerView.Adapter<HistoryMenuAdapter.ViewHolder>() {

    class ViewHolder(private val binding: HistoryMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(filteredDocsItem: FilteredDocsItem, makananResponses: List<MakananResponse>) {
            binding.tvDate.text = formatDate(filteredDocsItem.date)
            binding.tvSugarLevel.text = filteredDocsItem.kalori.toString()

            val food1 = makananResponses.find { it.data.nama == filteredDocsItem.food1 }
            val food2 = makananResponses.find { it.data.nama == filteredDocsItem.food2 }
            val food3 = makananResponses.find { it.data.nama == filteredDocsItem.food3 }

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
        val binding = HistoryMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filteredDocsItem = items[position]
        holder.bind(filteredDocsItem, makananResponses)

        holder.itemView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, DetailMenuActivity::class.java)

            intent.putExtra("menu_history", filteredDocsItem)
            intent.putParcelableArrayListExtra("makanan_responses", ArrayList(makananResponses))
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = items.size
}

private fun formatDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())

    return try {
        val date: Date = inputFormat.parse(dateString)
        outputFormat.format(date)
    } catch (e: Exception) {
        dateString
    }
}

