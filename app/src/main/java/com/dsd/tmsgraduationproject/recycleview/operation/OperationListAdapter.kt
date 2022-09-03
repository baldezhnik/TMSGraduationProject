package com.dsd.tmsgraduationproject.recycleview.operation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dsd.tmsgraduationproject.databinding.ItemOperationBinding
import com.dsd.tmsgraduationproject.room.OperationTuple
import com.dsd.tmsgraduationproject.room.entities.OperationEntity

class OperationListAdapter: ListAdapter<OperationTuple, OperationListAdapter.OperationViewHolder>(OperationsComparator())  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationViewHolder {
        return OperationViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: OperationViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class OperationViewHolder(private val itemBinding : ItemOperationBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(operationTuple: OperationTuple) {
            with(itemBinding){
                tvId.text = operationTuple.id.toString()
                tvType.text = operationTuple.type
                tvName.text = operationTuple.name
                tvSum.text = operationTuple.sum.toString()
                tvWalletName.text = operationTuple.nameWallet
            }
        }

        companion object {
            fun create(parent: ViewGroup): OperationViewHolder {
                val itemBinding = ItemOperationBinding.inflate(LayoutInflater
                    .from(parent.context), parent, false)
                return OperationViewHolder(itemBinding)
            }
        }
    }

    class OperationsComparator : DiffUtil.ItemCallback<OperationTuple>() {
        override fun areItemsTheSame(oldItem: OperationTuple, newItem: OperationTuple): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: OperationTuple, newItem: OperationTuple): Boolean {
            return oldItem.name == newItem.name
        }
    }
}