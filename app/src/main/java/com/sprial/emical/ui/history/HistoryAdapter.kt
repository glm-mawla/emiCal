package com.sprial.emical.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sprial.emical.R
import com.sprial.emical.data.EmiInfoModel
import com.sprial.emical.databinding.HistoryItemBinding
import com.sprial.emical.utils.formatCurrency

class HistoryAdapter(
    private val history: MutableList<EmiInfoModel>,
    onItemClick: (EmiInfoModel) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.HistoryVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH {
        return HistoryVH(
            HistoryItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
            )
        )
    }

    override fun getItemCount(): Int {
        return history.count()
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.bind(history[position])
    }

    inner class HistoryVH(
        private val binding: HistoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EmiInfoModel) {
            binding.itemWidget.calculateEmi(
                item.principal.toString(),
                item.interest.toString(),
                item.tenure.toString()
            )

            binding.itemWidget.hideFavourite()
            binding.principal.text = "Principal Amount : " + item.principal.formatCurrency()
        }

    }
}