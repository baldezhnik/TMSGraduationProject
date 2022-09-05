package com.dsd.tmsgraduationproject.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dsd.tmsgraduationproject.R
import com.dsd.tmsgraduationproject.databinding.FragmentUpdateWalletBinding
import com.dsd.tmsgraduationproject.room.OperationsApplication
import com.dsd.tmsgraduationproject.room.RoomViewModel
import com.dsd.tmsgraduationproject.room.RoomViewModelFactory
import com.dsd.tmsgraduationproject.room.entities.WalletEntity
import kotlinx.coroutines.runBlocking

class UpdateWalletFragment : Fragment() {

    companion object {
        const val WALLET_ID = "operationId"
    }
    private val roomViewModel: RoomViewModel by viewModels {
        RoomViewModelFactory((activity?.application as OperationsApplication).repository)
    }
    private var _binding: FragmentUpdateWalletBinding? = null
    private val binding get() = _binding!!
    private  lateinit var walletEntity: WalletEntity
    private var walletId: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        walletId = arguments?.getInt(WALLET_ID)!!
        _binding = FragmentUpdateWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getWalletEntity()

        binding.buttonUpdate.setOnClickListener {
            updateWallet()
        }

        binding.buttonDelete.setOnClickListener {
            roomViewModel.deleteWallet(walletEntity)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getWalletEntity(){
        runBlocking {
            walletEntity = roomViewModel.getWallet(walletId)
            binding.tvEditNameWallet.setText(walletEntity.name, TextView.BufferType.EDITABLE)
            binding.tvEditSumWallet.setText(walletEntity.sum.toString(), TextView.BufferType.EDITABLE)
            binding.buttonUpdate.isEnabled = true
            binding.buttonDelete.isEnabled = true
        }
    }

    private fun updateWallet() {
        if (TextUtils.isEmpty(binding.tvEditNameWallet.text)) {
            Toast.makeText(
                context,
                R.string.operation_empty,
                Toast.LENGTH_LONG
            ).show()
        } else {
            val walletEntity = WalletEntity(
                walletId,
                binding.tvEditNameWallet.text.toString(),
                binding.tvEditSumWallet.text.toString().toFloat())
            walletEntity.let { reply ->
                roomViewModel.updateWallet(reply)
            }
            findNavController().popBackStack()

        }
    }
}