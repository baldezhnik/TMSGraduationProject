package com.dsd.tmsgraduationproject.recycleview.operation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dsd.tmsgraduationproject.R
import com.dsd.tmsgraduationproject.databinding.ItemOperationBinding
import com.dsd.tmsgraduationproject.fragments.UpdateOperationFragment
import com.dsd.tmsgraduationproject.room.OperationTuple

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
                layout.setOnClickListener {
                    itemView.findNavController().navigate(
                        R.id.action_operation_fragment_to_updateOperationFragment,
                        bundleOf(UpdateOperationFragment.OPERATION_ID to operationTuple.id)
                    )
                }
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