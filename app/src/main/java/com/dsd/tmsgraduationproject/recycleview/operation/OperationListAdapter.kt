package com.dsd.tmsgraduationproject.recycleview.operation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dsd.tmsgraduationproject.databinding.ItemOperationBinding
import com.dsd.tmsgraduationproject.room.entities.OperationEntity

class OperationListAdapter: ListAdapter<OperationEntity, OperationListAdapter.OperationViewHolder>(OperationsComparator())  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationViewHolder {
        return OperationViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: OperationViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class OperationViewHolder(private val itemBinding : ItemOperationBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(operationEntity: OperationEntity) {
            with(itemBinding){
                tvId.text = operationEntity.id.toString()
                tvType.text = operationEntity.typeID.toString()
                tvName.text = operationEntity.name
                tvSum.text = operationEntity.sum.toString()
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

    class OperationsComparator : DiffUtil.ItemCallback<OperationEntity>() {
        override fun areItemsTheSame(oldItem: OperationEntity, newItem: OperationEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: OperationEntity, newItem: OperationEntity): Boolean {
            return oldItem.name == newItem.name
        }
    }
}