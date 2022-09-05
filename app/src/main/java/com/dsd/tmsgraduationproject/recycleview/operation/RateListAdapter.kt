package com.dsd.tmsgraduationproject.recycleview.operation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dsd.tmsgraduationproject.databinding.ItemRateBinding
import com.dsd.tmsgraduationproject.retrofit.RateItem

class RateListAdapter: ListAdapter<RateItem, RateListAdapter.RateViewHolder>(RatesComparator())  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        return RateViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class RateViewHolder(private val itemBinding : ItemRateBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(rateItem: RateItem) {
            with(itemBinding){
                tvCurRate.text = rateItem.Cur_OfficialRate.toString()
                tvScale.text = rateItem.Cur_Scale.toString()
                tvCurName.text = "${rateItem.Cur_Name} (${rateItem.Cur_Abbreviation})"
            }
        }

        companion object {
            fun create(parent: ViewGroup): RateViewHolder {
                val itemBinding = ItemRateBinding.inflate(
                    LayoutInflater
                        .from(parent.context), parent, false)
                return RateViewHolder(itemBinding)
            }
        }
    }

    class RatesComparator : DiffUtil.ItemCallback<RateItem>() {
        override fun areItemsTheSame(oldItem: RateItem, newItem: RateItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: RateItem, newItem: RateItem): Boolean {
            return oldItem.Cur_ID == newItem.Cur_ID
        }
    }
}
