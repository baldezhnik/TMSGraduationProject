package com.dsd.tmsgraduationproject.recycleview.operation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dsd.tmsgraduationproject.R
import com.dsd.tmsgraduationproject.databinding.ItemWalletBinding
import com.dsd.tmsgraduationproject.fragments.UpdateWalletFragment
import com.dsd.tmsgraduationproject.room.entities.WalletEntity

class WalletListAdapter: ListAdapter<WalletEntity, WalletListAdapter.WalletViewHolder>(WalletsComparator())  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        return WalletViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class WalletViewHolder(private val itemBinding : ItemWalletBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(walletEntity: WalletEntity) {
            with(itemBinding){
                tvIdWallet.text = walletEntity.id.toString()
                tvNameWallet.text = walletEntity.name
                tvSumWallet.text = walletEntity.sum.toString()
                layout.setOnClickListener {
                    itemView.findNavController().navigate(
                        R.id.action_wallet_fragment_to_updateWalletFragment,
                        bundleOf(UpdateWalletFragment.WALLET_ID to walletEntity.id)
                    )
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): WalletViewHolder {
                val itemBinding = ItemWalletBinding.inflate(
                    LayoutInflater
                    .from(parent.context), parent, false)
                return WalletViewHolder(itemBinding)
            }
        }
    }

    class WalletsComparator : DiffUtil.ItemCallback<WalletEntity>() {
        override fun areItemsTheSame(oldItem: WalletEntity, newItem: WalletEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: WalletEntity, newItem: WalletEntity): Boolean {
            return oldItem.name == newItem.name
        }
    }
}