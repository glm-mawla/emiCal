package com.sprial.emical.ui.themes.chip

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.sprial.emical.R
import com.sprial.emical.databinding.ChipItemBinding

class SingleSelectionAdapter(
    private val items: MutableList<Int>
) : RecyclerView.Adapter<SingleSelectionAdapter.SingleSelectionVH>() {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleSelectionVH {
        return SingleSelectionVH(
            ChipItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.chip_item, parent, false)
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SingleSelectionVH, position: Int) {
        holder.bind(items[position])

    }

    inner class SingleSelectionVH(private val binding: ChipItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Int) {
            binding.chipItem.text = "$item"
            if(selectedPosition == adapterPosition){
                binding.chipItem.setBackgroundResource(R.color.enabled)
            }else{
                binding.chipItem.setBackgroundResource(R.color.disabled)
            }

            binding.chipItem.setOnClickListener {
                setSingleSelection(adapterPosition)
            }
        }

    }

    private fun setSingleSelection(position: Int) {
//            if (position == RecyclerView.NO_POSITION) return

        notifyItemChanged(selectedPosition)
        selectedPosition = position
        notifyItemChanged(selectedPosition)
    }
}

